package com.altiora.ejercicio.service.implementation;

import com.altiora.ejercicio.dao.ArticleRepository;
import com.altiora.ejercicio.dao.ClientRepository;
import com.altiora.ejercicio.dao.OrderArticleRepository;
import com.altiora.ejercicio.dao.OrderRepository;
import com.altiora.ejercicio.dto.OrderRQ;
import com.altiora.ejercicio.dto.UpdateArticleRQ;
import com.altiora.ejercicio.dto.UpdateOrderRQ;
import com.altiora.ejercicio.exception.DataException;
import com.altiora.ejercicio.model.Article;
import com.altiora.ejercicio.model.Client;
import com.altiora.ejercicio.model.Order;
import com.altiora.ejercicio.model.OrderArticle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock

    private ClientRepository clientRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private OrderArticleRepository orderArticleRepository;

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService(orderRepository, orderArticleRepository, clientRepository, articleRepository);
    }

    //GET ALL ORDERS TEST
    @Test
    public void testGetAllOrdersNotEmpty() {
        List<Order> mockOrders = new ArrayList<>();
        mockOrders.add(new Order());
        mockOrders.add(new Order());

        when(orderRepository.findAll()).thenReturn(mockOrders);

        List<Order> orders = orderService.getAllOrders();

        assertNotNull(orders);
        assertEquals(2, orders.size());
    }


    //FIND BY ORDER TESTS

    @Test
    public void testFindByCodeOrderFound() {
        String orderCode = "w32k3f87aafa";
        Order existingOrder = new Order();
        when(orderRepository.findById(orderCode)).thenReturn(Optional.of(existingOrder));

        Order foundOrder = orderService.findByCode(orderCode);

        assertNotNull(foundOrder);
    }

    @Test
    public void testFindByCodeOrderNotFound() {
        String orderCode = "w32k3f87aafa";
        when(orderRepository.findById(orderCode)).thenReturn(Optional.empty());

        assertThrows(DataException.class, () -> {
            orderService.findByCode(orderCode);
        });
    }


    // CREATE TESTS

    @Test
    public void testAddNewOrderClientNotFound() {
        OrderRQ orderRQ = new OrderRQ();
        orderRQ.setClientCode(64);

        when(clientRepository.findById(orderRQ.getClientCode())).thenReturn(Optional.empty());

        assertThrows(DataException.class, () -> {
            orderService.addNewOrder(orderRQ);
        });
    }

    @Test
    public void testAddNewOrderClientFound() {
        OrderRQ orderRQ = new OrderRQ();
        orderRQ.setClientCode(1);
        orderRQ.setArticleList(Arrays.asList(1, 2, 3));

        Client client = new Client();
        when(clientRepository.findById(orderRQ.getClientCode())).thenReturn(Optional.of(client));

        Order mockOrder = new Order();
        mockOrder.setCode("w32k3f87aafa");
        when(orderRepository.saveAndFlush(any(Order.class))).thenReturn(mockOrder);

        String orderCode = orderService.addNewOrder(orderRQ);

        assertNotNull(orderCode);
    }

    @Test
    public void testAddNewOrderWithValidArticles() {
        OrderRQ orderRQ = new OrderRQ();
        orderRQ.setClientCode(1);
        orderRQ.setArticleList(Arrays.asList(1, 2, 3));

        Client client = new Client();
        when(clientRepository.findById(orderRQ.getClientCode())).thenReturn(Optional.of(client));

        Article article1 = new Article();
        when(articleRepository.findById(1)).thenReturn(Optional.of(article1));

        when(articleRepository.findById(2)).thenReturn(Optional.of(article1));


        Order mockOrder = new Order();
        mockOrder.setCode("w32k3f87aafa");
        when(orderRepository.saveAndFlush(any(Order.class))).thenReturn(mockOrder);

        String orderCode = orderService.addNewOrder(orderRQ);

        assertNotNull(orderCode);
    }


    // UPDATE TESTS

    @Test
    public void testUpdateOrderOrderNotFound() {
        UpdateOrderRQ updateOrderRQ = new UpdateOrderRQ();
        updateOrderRQ.setOrderCode("w32k3f87aafa");

        when(orderRepository.findById(updateOrderRQ.getOrderCode())).thenReturn(Optional.empty());

        assertThrows(DataException.class, () -> {
            orderService.updateOrder(updateOrderRQ);
        });
    }

    @Test
    public void testUpdateOrderAddArticle() {
        UpdateOrderRQ updateOrderRQ = new UpdateOrderRQ();
        updateOrderRQ.setOrderCode("w32k3f87aafa");

        Order existingOrder = new Order();
        when(orderRepository.findById(updateOrderRQ.getOrderCode())).thenReturn(Optional.of(existingOrder));

        UpdateArticleRQ updateArticleRQ = new UpdateArticleRQ();
        updateArticleRQ.setArticleCode(1);
        List<UpdateArticleRQ> updateArticleRQList = new ArrayList<>();
        updateArticleRQList.add(updateArticleRQ);
        updateOrderRQ.setUpdateArticleRQList(updateArticleRQList);

        Article article = new Article();
        when(articleRepository.findById(updateArticleRQ.getArticleCode())).thenReturn(Optional.of(article));


        orderService.updateOrder(updateOrderRQ);

        OrderArticle mockOrderArticle = new OrderArticle();
        mockOrderArticle.setOrder(existingOrder);
        mockOrderArticle.setArticle(article);

        verify(orderArticleRepository, times(1)).save(mockOrderArticle);

    }

    @Test
    public void testUpdateOrderRemoveArticle() {
        UpdateOrderRQ updateOrderRQ = new UpdateOrderRQ();
        updateOrderRQ.setOrderCode("w32k3f87aafa");

        Order existingOrder = new Order();
        when(orderRepository.findById(updateOrderRQ.getOrderCode())).thenReturn(Optional.of(existingOrder));

        UpdateArticleRQ updateArticleRQ = new UpdateArticleRQ();
        updateArticleRQ.setOrderArticleCode(1);
        List<UpdateArticleRQ> updateArticleRQList = new ArrayList<>();
        updateArticleRQList.add(updateArticleRQ);
        updateOrderRQ.setUpdateArticleRQList(updateArticleRQList);

        when(orderArticleRepository.findById(updateArticleRQ.getOrderArticleCode())).thenReturn(Optional.of(new OrderArticle()));

        orderService.updateOrder(updateOrderRQ);

        verify(orderArticleRepository, times(1)).deleteById(1);
    }

    @Test
    public void testUpdateArticleNotFound() {
        UpdateOrderRQ updateOrderRQ = new UpdateOrderRQ();
        updateOrderRQ.setOrderCode("w32k3f87aafa");

        Order existingOrder = new Order();
        when(orderRepository.findById(updateOrderRQ.getOrderCode())).thenReturn(Optional.of(existingOrder));

        UpdateArticleRQ updateArticleRQ = new UpdateArticleRQ();
        updateArticleRQ.setArticleCode(1);
        List<UpdateArticleRQ> updateArticleRQList = new ArrayList<>();
        updateArticleRQList.add(updateArticleRQ);
        updateOrderRQ.setUpdateArticleRQList(updateArticleRQList);

        when(articleRepository.findById(updateArticleRQ.getArticleCode())).thenReturn(Optional.empty());

        orderService.updateOrder(updateOrderRQ);
    }

    @Test
    public void testUpdateOrderArticleNotFound() {
        UpdateOrderRQ updateOrderRQ = new UpdateOrderRQ();
        updateOrderRQ.setOrderCode("w32k3f87aafa");

        Order existingOrder = new Order();
        when(orderRepository.findById(updateOrderRQ.getOrderCode())).thenReturn(Optional.of(existingOrder));

        UpdateArticleRQ updateArticleRQ = new UpdateArticleRQ();
        updateArticleRQ.setOrderArticleCode(1);
        List<UpdateArticleRQ> updateArticleRQList = new ArrayList<>();
        updateArticleRQList.add(updateArticleRQ);
        updateOrderRQ.setUpdateArticleRQList(updateArticleRQList);

        when(orderArticleRepository.findById(updateArticleRQ.getOrderArticleCode())).thenReturn(Optional.empty());

        orderService.updateOrder(updateOrderRQ);
    }


    // DELETE TESTS
    @Test
    public void testDeleteOrderOrderFound() {
        String orderCode = "w32k3f87aafa";
        Order existingOrder = new Order();
        when(orderRepository.findById(orderCode)).thenReturn(Optional.of(existingOrder));

        orderService.deleteOrder(orderCode);

        verify(orderRepository, times(1)).deleteById(orderCode);
    }

    @Test
    public void testDeleteOrderOrderNotFound() {
        String orderCode = "456";
        when(orderRepository.findById(orderCode)).thenReturn(Optional.empty());

        assertThrows(DataException.class, () -> {
            orderService.deleteOrder(orderCode);
        });

        verify(orderRepository, never()).deleteById(orderCode);
    }


}