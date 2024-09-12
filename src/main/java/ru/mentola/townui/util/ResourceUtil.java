package ru.mentola.townui.util;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;
import ru.mentola.townui.TownUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

@UtilityClass
public class ResourceUtil {
    @Nullable
    public static String getContentResource(String resource) {
        try (InputStream inputStream = TownUI.class.getResourceAsStream(resource);
             InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(inputStream));
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader))
        {
            StringBuilder source = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                source.append(line).append(System.lineSeparator());
            }
            return source.toString();
        } catch (IOException | NullPointerException exception) {
            return null;
        }
    }

    @Nullable
    public static InputStream getISResource(String resource) {
        return TownUI.class.getResourceAsStream(resource);
    }
}