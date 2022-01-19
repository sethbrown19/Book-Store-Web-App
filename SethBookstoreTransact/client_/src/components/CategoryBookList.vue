<template>
  <ul>
    <template v-for="book in $store.state.selectedCategoryBooks">
      <category-book-list-item :key="book.bookId" :book="book"></category-book-list-item>
    </template>
  </ul>
</template>

<script>
import CategoryBookListItem from "./CategoryBookListItem";

export default {
  name: "CategoryBookList",
  components: {
    CategoryBookListItem,
  },
};
</script>

<style scoped>
ul {
  display: flex;
  flex-wrap: wrap;
  padding: 2em;
  gap: 2em;
}
</style>
