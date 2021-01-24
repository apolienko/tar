# tar
Реализация утилиты, которая повзовляет объединить несколько текстовых файлов в один и наоборот.
## Использование
 Command Line: `java -jar [-u filename.txt] | file1.txt file2.txt [-out output.txt]`
 - `-u` — разбить файл (inputname.txt) на несколько исходных файлов.
- `-out` — объеденить файлы (file1.txt, file2.txt, ..) в один (output.txt).

Если входящие данные некорректны, генерируется сообщение для пользователя.




