package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.services.impl.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SongListServlet", urlPatterns = {"/songs/songs-list"})
public class SongListServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;
    private final SongServiceImpl songService;

    public SongListServlet(SpringTemplateEngine templateEngine, SongServiceImpl songService) {
        this.templateEngine = templateEngine;
        this.songService = songService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Song> songList;
        songList = songService.listSongs();

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("songList", songList);
        templateEngine.process("listSongs.html", context, resp.getWriter());
    }
}
