import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class VocabularyManager {
    public static void main(String[] args) {
        Set<String> knownWords = loadKnownWords();
        Set<String> wordsToLearn = new HashSet<>();

        String subtitlesFolderPath = "src/subtitles";

        File subtitlesFolder = new File(subtitlesFolderPath);
        File[] subtitleFiles = subtitlesFolder.listFiles();

        if (subtitleFiles != null && subtitleFiles.length > 0) {
            for (File subtitleFile : subtitleFiles) {
                if (subtitleFile.isFile() && subtitleFile.getName().toLowerCase().endsWith(".txt")) {
                    checkAndLearnWords(subtitleFile, knownWords, wordsToLearn);
                }
            }
        } else {
            System.out.println("Ошибка: в папке subtitles отсутствуют файлы с субтитрами.");
            return; // Прерываем выполнение программы, так как нет файлов для обработки.
        }

        if (wordsToLearn.isEmpty()) {
            System.out.println("Поздравляю! Ваши знания в порядке, выучивать ничего не нужно.");
        } else {
            writeWordsToFile(wordsToLearn);
            System.out.println("Слова, которые нужно выучить, записаны в файл learn-these-words.txt");
        }
    }

    private static Set<String> loadKnownWords() {
        Set<String> knownWords = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/dictionary/wordspool.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                knownWords.add(line.toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла со словарем: " + e.getMessage());
        }

        return knownWords;
    }

    private static void checkAndLearnWords(File subtitleFile, Set<String> knownWords, Set<String> wordsToLearn) {
        try (BufferedReader br = new BufferedReader(new FileReader(subtitleFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Используем регулярное выражение для разделения слов, учитывая апострофы и дефисы
                String[] words = line.split("[\\s]+");

                for (String word : words) {
                    // Используем регулярное выражение для удаления нежелательных символов
                    word = word.toLowerCase().replaceAll("[^a-zA-Z’'-]", "");

                    // Исключаем отдельные буквы
                    if (word.length() > 1 && !knownWords.contains(word)) {
                        wordsToLearn.add(word);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла с субтитрами " + subtitleFile.getName() + ": " + e.getMessage());
        }
    }

    private static void writeWordsToFile(Set<String> words) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/learn/learn-these-words.txt"))) {
            for (String word : words) {
                bw.write(word);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи слов в файл: " + e.getMessage());
        }
    }
}
