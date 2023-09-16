import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientComponent } from './components/client/client.component';
import { ArticleComponent } from './components/article/article.component';
import { OrderComponent } from './components/order/order.component';
import { LayoutComponent } from './layout/layout/layout.component';
import { TableModule } from 'primeng/table';
import { HttpClientModule } from '@angular/common/http';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';
import { MessagesModule } from 'primeng/messages';
import { MessageService } from 'primeng/api';
import { MenuModule } from 'primeng/menu';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DropdownModule } from 'primeng/dropdown';
import { MultiSelectModule } from 'primeng/multiselect';
import { EditOrderComponent } from './components/edit-order/edit-order.component';


@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    ArticleComponent,
    OrderComponent,
    LayoutComponent,
    EditOrderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TableModule,
    HttpClientModule,
    InputTextModule,
    FormsModule,
    ToastModule,
    ButtonModule,
    MessagesModule,
    MenuModule,
    BrowserAnimationsModule,
    DropdownModule,
    MultiSelectModule
  ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
