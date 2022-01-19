import Vue from 'vue'
import Vuex from 'vuex'
import ApiService from "@/services/ApiService";
import {ShoppingCart} from "@/models/ShoppingCart";

Vue.use(Vuex)
export const CART_STORAGE_KEY = 'cart'
export const ORDER_DETAIL_STORAGE_KEY = 'orderDetail'

let orderDetail = window.localStorage.getItem('orderDetail');
export default new Vuex.Store({
    state: {
        categories: [],
        selectedCategoryName: "",
        selectedCategoryBooks: [],
        cart: new ShoppingCart(),
        orderDetail: orderDetail ? JSON.parse(orderDetail) : null,
    },
    mutations: {
        SET_CATEGORIES(state, newCategories) {
            state.categories = newCategories;
        },
        ADD_TO_CART(state, book, quantity = 1) {
            state.cart.addItem(book, quantity);
            localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart))

        },
        SELECT_CATEGORY(state, categoryName) {
            state.selectedCategoryName = categoryName;
        },
        SET_SELECTED_CATEGORY_BOOKS(state, book) {
            state.selectedCategoryBooks = book;
        },
        UPDATE_CART(state, {book, quantity}) {
            state.cart.update(book, quantity);
            localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
        },
        SET_CART(state, shoppingCart) {
            localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(shoppingCart));
            let newCart = new ShoppingCart();
            shoppingCart.items.forEach(item => {
                newCart.addItem(item.book, item.quantity);
            });
            state.cart = newCart;
        },
        CLEAR_CART(state) {
            state.cart.clear();
            localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
        },
        CLEAR_ORDER_DETAIL(state) {
            sessionStorage.removeItem(ORDER_DETAIL_STORAGE_KEY)
            state.orderDetail = null
        },
        SET_ORDER_DETAILS(state, customerForm) {
            state.orderDetail = customerForm;
            sessionStorage.setItem(ORDER_DETAIL_STORAGE_KEY, JSON.stringify(this.state.orderDetail));
        }
    },
    actions: {
        fetchCategories(context) {
            ApiService.fetchCategories()
                .then(categories => {
                    console.log("Data: ", categories);
                    context.commit("SET_CATEGORIES", categories);
                })
                .catch(reason => {
                    console.log("Error: " + reason);
                })
        },
        addToCart(context, book) {
            context.commit("ADD_TO_CART", book);
        },
        selectCategory(context, name) {
            context.commit("SELECT_CATEGORY", name);
        },
        fetchSelectedCategoryBooks(context, book) {
            ApiService.fetchSelectedCategoryBooks(book)
                .then(selectedCategoryBooks => {
                    console.log("Data: " + selectedCategoryBooks);
                    context.commit("SET_SELECTED_CATEGORY_BOOKS", selectedCategoryBooks);
                })
                .catch(reason => {
                    console.log("Error: " + reason);
                    throw new Error(reason);
                })
        },
        updateCart(context, {book, quantity}) {
            context.commit("UPDATE_CART", {book, quantity});
        },
        clearCart(context) {
            context.commit("CLEAR_CART");
        },
        placeOrder(context, customerForm) {
            context.commit('CLEAR_ORDER_DETAIL')

            return ApiService.placeOrder({
                cart: context.state.cart,
                customerForm: customerForm
            }).then(orderDetail => {
                context.commit("CLEAR_CART");
                context.commit("SET_ORDER_DETAILS", orderDetail);
            });
        },

    },
    getters: {
        surcharge(state) {
            return state.surcharge
        },
        categoryName(state) {
            return state.selectedCategoryName
        }
    }
});