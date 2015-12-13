package br.com.jup.facade;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by renatorodrigues on 13/12/15.
 */
public class Teste {

    public static void main(String args[]) throws IOException {

        Properties properties = new Properties();
        InputStream inputStream = Teste.class.getClassLoader().getResourceAsStream("config.properties");

        properties.load(inputStream);

        String property = properties.getProperty("jup.messages.command.receiveMessage");

        System.out.println(property);


    }
}
