<template>
  <footer class="container">
    <div class="copyright">
      <p>&copy; All Booked Up 2021</p>
    </div>
    <section class="links">
      <div>
        <p>Company Information</p>
      </div>
      <router-link to="/"
      >About&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
      </router-link
      >
      <router-link to="/"
      >Contact&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
      </router-link
      >
      <router-link to="/">Directions</router-link>
    </section>
    <section class="social-media-icons">
      <router-link to="/" class="cart-sign-in-button"
      ><i class="fab fa-twitter-square fa-2x"></i>&nbsp;&nbsp;
      </router-link
      >
      <router-link to="/" class="cart-sign-in-button"
      ><i class="fab fa-facebook-square fa-2x"></i
      ></router-link>
    </section>
  </footer>
</template>

<script>
export default {
  name: "AppFooter",
};
</script>

<style scoped>
footer {
  background: white;
  display: grid;
  gap: 1em;
  min-height: 120px;
  max-height: 150px;
  grid-template-columns: 1fr 1fr 1fr;
}

.copyright {
  display: inline-block;
  text-align: left;
  padding-top: 30px;
  padding-left: 15px;
  font-size: 18px;
}

.social-media-icons {
  display: inline-block;
  text-align: right;
  padding-top: 20px;
  padding-right: 15px;
  border-color: none;
}

.social-media-icons a {
  text-decoration: none;
}

.links {
  display: inline-block;
  padding-top: 10px;
  padding-left: 10px;
  font-size: 18px;
  text-align: center;
}

.links a {
  text-decoration: none;
}
</style>
