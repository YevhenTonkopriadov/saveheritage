package ua.gov.mkip.craft.controllers;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.gov.mkip.craft.models.User;
import ua.gov.mkip.craft.services.QrServises;
import ua.gov.mkip.craft.services.UserCraftsManService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class QrImageControler {

    private final UserCraftsManService userCraftsManService;
    private final QrServises qrServises;

    @GetMapping("image/display")
    @ResponseBody
    void showImage(HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        response.getOutputStream().write(userCraftsManService.findByUsername(user.getUsername()).getCraftsManImage());
        response.getOutputStream().close();
    }

    @GetMapping("/qr/{typeObjectQr}/{id}")
    @ResponseBody
    void generateQr(HttpServletResponse response, @PathVariable ("id") Long id, @PathVariable ("typeObjectQr")String typeObjectQr ) throws ServletException, IOException, WriterException {
        response.setContentType("image/png");
        response.getOutputStream().write(qrServises.generateQrByte("https://www.craftman.mkip.gov.ua/"+typeObjectQr+"/"+id));
        response.getOutputStream().close();
    }
}
