package com.koreait.board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mod")
public class BoardModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String strIboard = req.getParameter("iboard");
        int iboard = Integer.parseInt(strIboard);

        BoardVO param = new BoardVO();

        param.setIboard(iboard);

        BoardVO data = BoardDAO.selBoardDetail(param);
        //211109 6-33:50
        req.setAttribute("data", data);


        String path = "WEB-INF/jsp/mod.jsp";
        RequestDispatcher rd = req.getRequestDispatcher(path);
        rd.forward(req, res);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String strIboard = req.getParameter("iboard");
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");
        String writer = req.getParameter("writer");

        int iboard = Integer.parseInt(strIboard);

        BoardVO param = new BoardVO();

        param.setIboard(iboard);
        param.setTitle(title);
        param.setCtnt(ctnt);
        param.setWriter(writer);

        int result = BoardDAO.updBoard(param);

        switch (result) {
            case 1:
                res.sendRedirect("/detail?iboard=" + strIboard);
                break;
        }
    }
}
