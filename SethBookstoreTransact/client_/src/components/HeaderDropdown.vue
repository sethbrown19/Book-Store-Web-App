<template>
  <div class="main-menu-container">
    <button class="button categories-button"
            @click="toggleMainMenu">
      <i class="fas fa-bars fa-2x"></i>
    </button>
    <ul class="main-menu-panel" :class="{ 'main-menu-open': mainMenuOpen }">
      <li class="sub-menu-container">
        <button class="main-menu-item sub-menu-button" @click="toggleSubMenu">
          <div v-if="!subMenuOpen">
            Categories &#9660;
          </div>
          <div v-else-if="subMenuOpen">
            Categories &#9650;
          </div>
        </button>
        <ul class="sub-menu-panel" :class="{ 'sub-menu-open': subMenuOpen }">
          <li v-for="category in $store.state.categories" :key="category.categoryId" class="sub-menu-item">
            <router-link :to="'/category/' + category.name" @click.native="toggleMainMenu">
              {{ category.name }}
            </router-link>
          </li>
        </ul>

      </li>
      <template @click.native="closeMenu">
        <router-link to="/" tag="li" class="main-menu-item" @click.native="toggleMainMenu">
          Account
        </router-link>
        <router-link to="/cart" tag="li" class="main-menu-item" @click.native="toggleMainMenu">
          Cart (<span>{{ $store.state.cart.numberOfItems }}</span>)
        </router-link>
        <router-link to="/" tag="li" class="main-menu-item" @click.native="toggleMainMenu">
          Home
        </router-link>
      </template>
    </ul>
  </div>


</template>

<script>

export default {
  name: "HeaderDropdownMenu",
  data: function () {
    return {
      mainMenuOpen: false,
      subMenuOpen: false,
    };
  },
  methods: {
    toggleMainMenu() {
      this.mainMenuOpen = !this.mainMenuOpen;
      if (!this.mainMenuOpen && this.subMenuOpen) {
        this.subMenuOpen = false;
      }
    },
    toggleSubMenu() {
      this.subMenuOpen = !this.subMenuOpen;
    },
  },
};
</script>

<style scoped>
.main-menu-container {
  display: inline-block;
}

.main-menu-panel {
  position: absolute;
  right: -500rem;
  margin-top: .215rem;
  transform: translatex(100%);
  transition-duration: 200ms;
  transition-property: transform;
  transition-timing-function: ease-in-out;
  background-color: white;
  cursor: pointer;
  z-index: 1;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
  min-width: 8em;
  max-width: 10em;
}

.main-menu-panel.main-menu-open {
  transform: translateX(0);
  right: 0;
}

.main-menu-item {
  display: inline-block;
  white-space: nowrap;
  text-decoration: none;
  width: 100%;
  height: 100%;
  padding: 1em 1.75em;
  border-top: 1px solid white;
  border-left: 1px solid white;
  border-right: 1px solid white;
  border-bottom: 1px solid white;

}

.main-menu-item:hover {
  background-color: var(--primary-background-color);
}

a,
a:active,
a:hover,
a:visited {
  color: #602656;
  text-decoration: none;
}

.sub-menu-panel {
  max-height: 0;
  transition: max-height 400ms ease-in-out;
  background-color: lightgrey;
  overflow-y: hidden;

}

.sub-menu-panel.sub-menu-open {
  max-height: 10em;
}


.sub-menu-item {
  display: inline-block;
  white-space: nowrap;
  text-decoration: none;
  width: 100%;
  height: 100%;
  padding: .4em 1.75em;
}

.sub-menu-item:hover {
  background-color: white;
  color: var(--primary-color);
}

.sub-menu-button {
  background-color: white;
  color: #602656;
  cursor: pointer;
  border: solid white 1px;
}

.sub-menu-container:hover .sub-menu-panel {
  display: block;
}

.categories-button,
.categories-button:visited {
  background-color: inherit;
  color: var(--primary-color);
  cursor: pointer;
  border: solid var(--header-background-color) 2px;
  padding: 5px 20px;
}

.categories-button:hover,
.categories-button:active {
  background-color: lightgray;
}

</style>
