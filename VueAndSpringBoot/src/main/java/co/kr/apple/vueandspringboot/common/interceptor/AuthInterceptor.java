package co.kr.apple.vueandspringboot.common.interceptor;

import co.kr.apple.vueandspringboot.menu.model.Menu;
import co.kr.apple.vueandspringboot.menu.repository.MenuRepository;
import co.kr.apple.vueandspringboot.post.model.Post;
import co.kr.apple.vueandspringboot.post.repository.PostRepository;
import co.kr.apple.vueandspringboot.search.model.Board;
import co.kr.apple.vueandspringboot.search.repository.BoardRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Comparator;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final MenuRepository menuRepository;
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(">>> preHandel 호출: " + request.getRequestURI());

        HttpSession session = request.getSession();
        int userAuth = getUserAuth(session);

        Integer menuId = getParamInt("menuId", request);
        Integer boardId = getParamInt("boardId", request);
        Integer postId = getParamInt("postId", request);

        if (menuId != null) {
            Optional<Menu> omenu = menuRepository.findById(menuId);
            Menu menu = omenu.orElseThrow(() -> new RuntimeException("해당 주소가 존재하지 않습니다."));

            if (userAuth < menu.getMenuAuth()) {
                throw new RuntimeException("접속 권한이 없습니다.");
            }
        } else {
            if(postId != null){
                Optional<Post> opost = postRepository.findById(postId);
                Post post = opost.orElseThrow(()->new RuntimeException("해당 주소가 존재하지 않습니다."));
                boardId = post.getBoardId();
            }
            if (boardId != null) {
                Optional<Board> oboard = boardRepository.findById(boardId);
                Board board = oboard.orElseThrow(() -> new RuntimeException("해당 주소가 존재하지 않습니다."));
                Integer boardAuth = board.getMenuList().stream()
                        .map(Menu::getMenuAuth)
                        .min(Integer::compareTo)
                        .orElse(0);

                if (userAuth < boardAuth)
                    throw new RuntimeException("해당 주소에 접근 권한이 없습니다.");
            }
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private Integer getUserAuth(HttpSession session) {
        Object userAuthObj = session.getAttribute("userAuth");

        if (userAuthObj != null)
            return Integer.parseInt(String.valueOf(userAuthObj));
        else
            return 0;
    }

    private Integer getParamInt(String argName, HttpServletRequest request) {
        String paramStr = request.getParameter(argName);
        if (paramStr != null)
            return Integer.parseInt(paramStr);
        return null;
    }
}
