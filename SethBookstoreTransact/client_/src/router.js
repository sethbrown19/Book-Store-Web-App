import Vue from "vue";
import VueRouter from "vue-router";
import Home from "./views/Home.vue";
import Category from "./views/Category.vue"
import Cart from "@/views/Cart.vue";
import Checkout from "@/views/Checkout.vue";
import Confirmation from "@/views/Confirmation";
import NotFound from "@/components/NotFound";

Vue.use(VueRouter);

export default new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [
        {
            path: "/home",
            name: "home",
            component: Home,
            alias: ['/', '/index.html', '/home', '']
        },

        {
            path: "/category/:name",
            name: "category",
            component: Category,
            props: true,
        },
        {
            path: "/category",
            redirect: "/category/Best Sellers",
            name: "category",
            component: Category,
            props: true,
        },
        {
            path: "/cart",
            name: "cart",
            component: Cart,
        },
        {
            path: "/checkout",
            name: "checkout",
            component: Checkout,
        },
        {
            path: "/confirmation",
            name: "confirmation",
            component: Confirmation,
        },
        {
            path: '*',
            name: "not found",
            component: NotFound
        },
    ],
});
