import axios from "axios";
import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const useSearchStore = defineStore("searchStore", () => {
  const searchInfo = ref({
    keyword: "",
    boards: [],
    pages: [],
    howKeyword: [],
  });
  const getBoards = computed(() => {
    return searchInfo.value.boards;
  });
  const setKeyword = (keyword) => {
    searchInfo.value.keyword = keyword;
  };
  const search = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/apple/search/init/title?keyword=${searchInfo.value.keyword}`
      );
      searchInfo.value.boards = response.data;
      searchInfo.value.pages = Array(response.data.length).fill(1);
      searchInfo.value.boards.sort((a, b) => a.boardId - b.boardId);
    } catch (error) {
      console.error(error);
      alert("검색에 실패했습니다.");
    }
  };
  const getResultPage = async (page, index, boardId) => {
    searchInfo.value.pages[index] = page;
    try {
      const response = await axios.get(
        `http://localhost:8080/api/apple/search/data/title/${boardId}?keyword=${searchInfo.value.keyword}&page=${searchInfo.value.pages[index]}`
      );
      searchInfo.value.boards.forEach((board, idx) => {
        if (board.boardId == response.data.boardId) {
          searchInfo.value.boards[idx].postList = response.data.postList;
          return;
        }
      });
      return response;
    } catch (error) {
      console.error(error);
      alert("정보를 불러오는데 실패했습니다.");
    }
  };
  const getHotKeywords = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/apple/search/hot-keys`
      );
      searchInfo.value.howKeyword = response.data;
    } catch (error) {
      console.error(error);
    }
  };
  const hotKeyword = computed(() => {
    return searchInfo.value.howKeyword;
  });

  return {
    searchInfo,
    getBoards,
    hotKeyword,
    setKeyword,
    search,
    getResultPage,
    getHotKeywords,
  };
});
