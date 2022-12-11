package com.example.springBootaws.web;

import com.example.springBootaws.config.auth.LoginUser;
import com.example.springBootaws.config.auth.dto.SessionUser;
import com.example.springBootaws.service.post.PostService;
import com.example.springBootaws.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    public final PostService postService;
    public final HttpSession httpSession;


    @GetMapping("/post/save")
    public String postSave() {
        return "post-save";
    }

    @GetMapping("/")
    public String index(Model model,
                        @LoginUser SessionUser user,
                        @RequestParam("page") int page,
                        @RequestParam("size") int size) {
        model.addAttribute("posts", postService.findAllDesc(page - 1, size));


        if(user != null) {
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }

    @GetMapping("/post/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostResponseDto dto = postService.findById(id);
        model.addAttribute("posts", dto);

        return "post-update";
    }

    @GetMapping
    public String search(Model model,
                         @RequestParam("page") int page,
                         @RequestParam("size") int size,
                         @RequestParam(value = "search",required = false) String search ) {
        model.addAttribute("posts", postService.findAllByTitleOrContent(search,page - 1, size));

        return "post-search";
    }
}
