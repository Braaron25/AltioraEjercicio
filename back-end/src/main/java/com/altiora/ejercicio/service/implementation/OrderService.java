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
import com.altiora.ejercicio.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    private OrderRepository orderRepository;

    private OrderArticleRepository orderArticleRepository;

    private ClientRepository clientRepository;

    private ArticleRepository articleRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderArticleRepository orderArticleRepository, ClientRepository clientRepository, ArticleRepository articleRepository) {
        this.orderRepository = orderRepository;
        this.orderArticleRepository = orderArticleRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order findByCode(String code){
        Optional<Order> orderOptional = this.orderRepository.findById(code);
        if(!orderOptional.isPresent()){
            throw new DataException("No se encontro la orden");
        }

        return orderOptional.get();
    }

    @Override
    public String addNewOrder(OrderRQ orderRQ) {

        Optional<Client> clientOptional = this.clientRepository.findById(orderRQ.getClientCode());

        if(!clientOptional.isPresent()){
            throw new DataException("No se encontro al cliente");
        }

        Order newOrder = new Order();
        newOrder.setClient(clientOptional.get());
        newOrder.setDate(new Date());

        newOrder = this.orderRepository.saveAndFlush(newOrder);

        for(Integer articleCode : orderRQ.getArticleList()){
            Optional<Article> articleOptional = this.articleRepository.findById(articleCode);
            if (!articleOptional.isPresent()){
                continue;
            }
            OrderArticle detail = new OrderArticle();
            detail.setOrder(newOrder);
            detail.setArticle(articleOptional.get());
            this.orderArticleRepository.save(detail);
        }

        return newOrder.getCode();
    }

    @Override
    public void updateOrder(UpdateOrderRQ updateOrderRQ) {
        Optional<Order> orderOptional = this.orderRepository.findById(updateOrderRQ.getOrderCode());
        if (!orderOptional.isPresent()){
            throw new DataException("No se encontro la orden");
        }

        for(UpdateArticleRQ updateArticleRQ : updateOrderRQ.getUpdateArticleRQList()){
            if(updateArticleRQ.getOrderArticleCode() == null ){
                Optional<Article> articleOptional = this.articleRepository.findById(updateArticleRQ.getArticleCode());
                if (!articleOptional.isPresent()){
                    continue;
                }
                OrderArticle detail = new OrderArticle();
                detail.setOrder(orderOptional.get());
                detail.setArticle(articleOptional.get());
                this.orderArticleRepository.save(detail);
            }else{
                Optional<OrderArticle> optionalOrderArticle = this.orderArticleRepository.findById(updateArticleRQ.getOrderArticleCode());
                if (!optionalOrderArticle.isPresent()){
                    continue;
                }

                this.orderArticleRepository.deleteById(updateArticleRQ.getOrderArticleCode());
            }
        }
    }

    @Override
    public void deleteOrder(String cod_order) {
        Optional<Order> orderOptional = this.orderRepository.findById(cod_order);
        if (!orderOptional.isPresent()) {
            throw new DataException("No se encontro la orden");
        }

        this.orderRepository.deleteById(cod_order);
    }
}
