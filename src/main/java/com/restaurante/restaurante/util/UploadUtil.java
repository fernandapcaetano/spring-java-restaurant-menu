package com.restaurante.restaurante.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
    public static boolean fazerUploadImagem(MultipartFile imagem) {
        boolean sucessoUpload = false;
        if (!imagem.isEmpty()) {
            String nomeArquivo = imagem.getOriginalFilename();
            try {
                // Criando diretório para armazenar o arquivo
                String pastaUploadImagem = "C:\\Users\\Marili\\Desktop\\Nanda\\FATEC\\Desenvolvimento para Servidores I\\RestauranteV2\\restaurante\\src\\main\\resources\\static\\images\\pratos";
                File dir = new File(pastaUploadImagem);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Criando o arquivo no diretório
                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(imagem.getBytes());
                stream.close();
                sucessoUpload = true;
            } catch (Exception e) {
                System.out.println("Você falhou! " + e.getMessage());
            }
        } else {
            System.out.println("Você falhou!");
        }
        return sucessoUpload;
    }
}
