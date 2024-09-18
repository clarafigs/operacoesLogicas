import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {

            BufferedImage image1 = ImageIO.read(new File("imagensOP/image7.jpg"));
            BufferedImage image2 = ImageIO.read(new File("imagensOP/image8.jpg"));


            BufferedImage resizedImage1 = resizeImage(image1, 512, 512);
            BufferedImage resizedImage2 = resizeImage(image2, 512, 512);


            BufferedImage resultImage = addImages(resizedImage1, resizedImage2);


            ImageIO.write(resultImage, "jpg", new File("imagensOP/adicao7_8.jpg"));

            System.out.println("Adição de imagens com normalização concluída com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

    public static BufferedImage addImages(BufferedImage img1, BufferedImage img2) {
        int width = img1.getWidth();
        int height = img1.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int pixel1 = new Color(img1.getRGB(x, y)).getRed();
                int pixel2 = new Color(img2.getRGB(x, y)).getRed();

                int pixelResult = (pixel1 + pixel2) / 2;

                Color newColor = new Color(pixelResult, pixelResult, pixelResult);
                result.setRGB(x, y, newColor.getRGB());
            }
        }

        return result;
    }
}

