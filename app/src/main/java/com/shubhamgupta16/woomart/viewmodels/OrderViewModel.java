package com.shubhamgupta16.woomart.viewmodels;

import androidx.lifecycle.ViewModel;

import com.shubhamgupta16.woomart.common.WooLiveData;
import com.shubhamgupta16.woomart.repo.OrderRepository;
import com.shubhamgupta16.woomart.repo.ProductRepository;

import java.util.List;

import javax.inject.Inject;

import me.gilo.woodroid.models.Order;
import me.gilo.woodroid.models.Product;
import me.gilo.woodroid.models.filters.OrderFilter;
import me.gilo.woodroid.models.filters.ProductFilter;


public final class OrderViewModel extends ViewModel {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Inject
    OrderViewModel(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository =  orderRepository;
        this.productRepository = productRepository;
    }

    public WooLiveData<Order> addToCart(int productId) {
        return orderRepository.addToCart(productId);
    }

    public WooLiveData<Order> create(Order order) {
       return orderRepository.create(order);
    }


    public WooLiveData<Order> order(int id) {

        return orderRepository.order(id);
    }

    public WooLiveData<List<Product>> products(ProductFilter filter) {
        return productRepository.products(filter);
    }

    public WooLiveData<List<Order>> orders() {
        return orderRepository.orders();
    }

    public WooLiveData<List<Order>> orders(OrderFilter orderFilter) {
        return orderRepository.orders(orderFilter);
    }

    public WooLiveData<Order> update(int id, Order order) {
        return orderRepository.update(id, order);
    }

    public WooLiveData<Order> delete(int id) {
        return orderRepository.delete(id);
    }

    public WooLiveData<Order> delete(int id, boolean force) {
        return orderRepository.delete(id, force);
    }

}