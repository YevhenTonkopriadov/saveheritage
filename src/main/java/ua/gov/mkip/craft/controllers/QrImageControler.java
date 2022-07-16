package ua.gov.mkip.craft.controllers;

import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.gov.mkip.craft.services.ImageServises;
import ua.gov.mkip.craft.services.QrServises;
import ua.gov.mkip.craft.services.UserCraftsManService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class QrImageControler {

    @Value("${server.url}")
    private String url;

    private final UserCraftsManService userCraftsManService;
    private final ImageServises imageServises;
    private final QrServises qrServises;

    @GetMapping("image/display/{typeObjectImage}/{id}")
    @ResponseBody
    void showImage(HttpServletResponse response, @PathVariable("typeObjectImage") String typeObjectImage, @PathVariable("id") Long id) {
        try {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            if (typeObjectImage.equals("user")) {
                response.getOutputStream().write(userCraftsManService.findById(id).get().getCraftsManImage());
            }
            if (typeObjectImage.equals("recordImage")){
                response.getOutputStream().write(imageServises.findById(id).get().getImage());
            }
            response.getOutputStream().close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/qr/{typeObjectQr}/{id}")
    @ResponseBody
    void generateQr(HttpServletResponse response, @PathVariable ("id") Long id, @PathVariable ("typeObjectQr")String typeObjectQr ) {
        try {
            response.setContentType("image/png");
            response.getOutputStream().write(qrServises.generateQrByte(url+ typeObjectQr + "viewer/" + id));
            response.getOutputStream().close();
        } catch (IOException | WriterException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
