//Created By: Krishna Patidar , (Batch 3)


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import java.awt.*;

public class img_editor {
    
    //Function to convert an image to grayScale.
     
    public static BufferedImage convertToGrayScale(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height,
            BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                outputImage.setRGB(j, i, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }

    // rotate 90 degree anti-clockwise image
    public static BufferedImage rotateImage(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                outputImage.setRGB(i, j, inputImage.getRGB(j, i));
            }
        }
        return outputImage;
    }

    //Rotate 90 degree clockwise
    public static BufferedImage rotate90(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inputImage.getRGB(j, i));
                outputImage.setRGB(height-i-1, j, pixel.getRGB());
            }
        }
        return outputImage;
    }

    //crop image to first quadrant
    public static BufferedImage cropImage(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(height/2, width/2, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height/2; i++) {
            for (int j = 0; j < width/2; j++) {
                outputImage.setRGB(i, j, inputImage.getRGB(i, j));
            }
        }
        return outputImage;
    }

    //Inversion (vertical Flip)
    static BufferedImage flipVertical(BufferedImage inputImage){
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                outputImage.setRGB(j, i, inputImage.getRGB(j, height-i-1));
            }
        }
        return outputImage;
    }

    public static BufferedImage changeBrightness(BufferedImage inputImage, int increase) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = new Color(inputImage.getRGB(j, i));
                int red = pixel.getRed();
                int blue = pixel.getBlue();
                int green = pixel.getGreen();
                red = red + (increase * red / 100);
                blue = blue + (increase * blue) / 100;
                green = green + (increase * green) / 100;
                if (red > 255)
                    red = 255;
                if (blue > 255)
                    blue = 255;
                if (green > 255)
                    green = 255;

                if (red < 0)
                    red = 0;
                if (blue < 0)
                    blue = 0;
                if (green < 0)
                    green = 0;
                Color newPixel = new Color(red, green, blue);
                outputImage.setRGB(j, i, newPixel.getRGB());

            }
        }
        return outputImage;

    }

    //Blur Image
    public static BufferedImage BlurTheImage(BufferedImage image,int density){
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage finalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //Running a Loop According to the Density Given
        //Selecting a small matrix in image
        for(int i=0;i<height;i+=density){
            for(int j=0;j<width;j+=density){
                int avgRed=0;
                int avgGreen=0;
                int avgBlue=0;
                int count=0;
                //Calculating the average rbg value of the matrix
                for(int k=i;k<i+density && k<height;k++){
                    for(int l=j;l<j+density && l<width;l++){
                        //Here We Calculate The Average of The Pixels
                        Color pixel = new Color(image.getRGB(l, k));
                        avgRed+=pixel.getRed();
                        avgGreen+=pixel.getGreen();
                        avgBlue+=pixel.getBlue();
                        count++;
                    }
                }
                avgRed/=count;
                avgGreen/=count;
                avgBlue/=count;
                //assigning the average value to the complete matrix
                for(int k=i;k<i+density && k<height;k++){
                    for(int l=j;l<j+density && l<width;l++){
                        Color newPixel = new Color(avgRed,avgGreen,avgBlue);
                        finalImage.setRGB(l, k, newPixel.getRGB());
                    }
                }
            }
        }
        return finalImage;
    }



    public static void printPixelValues(BufferedImage inputImage) {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // System.out.print(inputImage.getRGB(j, i) + " ");
                Color pixel = new Color(inputImage.getRGB(j, i));
                System.out.print("(" + pixel.getRed() + " " + pixel.getBlue() + " "
                        + pixel.getGreen() + ")");
                // pixel.getGreen());
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        File inputFile = new File("micy.jpg");
        try {
            BufferedImage inputImage = ImageIO.read(inputFile);
            boolean flag=true;
            while(flag){
            int ch;
            System.out.println("1.Print Pixels \n2.Convert to gray scale\n3.Increase brightness \n4.Rotate image \n5.Crop to 1st quadrant of image \n6.Blur image \n7.Rotate 90\n8.Flip image \n9.Exit");
            System.out.println("Enter your choice");
            ch=sc.nextInt();
            switch(ch)
            {
                case 1:
                    printPixelValues(inputImage);
                    break;
                case 2:
                    BufferedImage convertedToGray = convertToGrayScale(inputImage);
                    File convertedToGrayImage = new File("convertedToGrayImage.jpg");
                    ImageIO.write(convertedToGray, "jpg", convertedToGrayImage);
                    System.out.println("Image converted to gray scale !!!");
                    break;
                case 3:
                    BufferedImage changedBrightness = changeBrightness(inputImage, 25);
                    File changedBrightnessImage = new File("changedBrightnessImage.jpg");
                    ImageIO.write(changedBrightness, "jpg", changedBrightnessImage);
                    System.out.println("Brightness changed !!!");
                    break;
                case 4:
                    BufferedImage rot = rotateImage(inputImage);
                    File rotImage = new File("rotatedImg.jpg");
                    ImageIO.write(rot, "jpg", rotImage);
                    System.out.println("Image rotated !!!");
                    break;
                case 5:
                    BufferedImage crop = cropImage(inputImage);
                    File cropImage = new File("cropImage.jpg");
                    ImageIO.write(crop, "jpg", cropImage);
                    System.out.println("Image cropped !!!");
                    break;
                case 6:
                    BufferedImage blur = BlurTheImage(inputImage, 2);
                    File blurImage = new File("blurImage.jpg");
                    ImageIO.write(blur, "jpg", blurImage);
                    System.out.println("Image blurred !!!");
                    break;
                case 7:
                    BufferedImage rot90 = rotate90(inputImage);
                    File rot90Image = new File("rot90Image.jpg");
                    ImageIO.write(rot90, "jpg", rot90Image);
                    System.out.println("Image rotated 90 !!!");
                    break;
                case 8:
                    BufferedImage flip = flipVertical(inputImage);
                    File flipImage = new File("flipImage.jpg");
                    ImageIO.write(flip, "jpg", flipImage);
                    System.out.println("Image flipped !!!");
                    break;
                case 9:
                    flag=false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
