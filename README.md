# dictionary-for-watching-foreign-videos

dictionary-for-watching-foreign-videos — это программа на Java для изучения иностранного языка через просмотр фильмов, всё что нужно - иметь субтитры к фильму, таким образом, Вы заранее выучите новые слова перед просмотром фильма.

## Как использовать

1. **Подготовка:**
   - Создайте файл `wordspool.txt` в папке `src/dictionary` и добавьте в него известные вам слова (каждое слово на новой строке), по мере обучения Вы будете редактировать этот файл.

2. **Запуск:**
   - Запустите программу. Она автоматически проверит файлы с субтитрами в папке `src/subtitles` и определит слова, которые Вам нужно выучить.

3. **Результат:**
   - Новые слова будут записаны в файл `learn-these-words.txt` в папке `src/learn`.

## Требования

- Java Runtime Environment (JRE)

## Как собрать и запустить

1. Убедитесь, что у Вас установлен JDK (Java Development Kit).
2. Склонируйте репозиторий: `git clone https://github.com/Username-Andrey-is-available/dictionary-for-watching-foreign-videos.git`
3. Перейдите в папку проекта: `cd dictionary-for-watching-foreign-videos`
4. Соберите проект: `javac dictionary-for-watching-foreign-videos.java`
5. Запустите программу: `java dictionary-for-watching-foreign-videos`
