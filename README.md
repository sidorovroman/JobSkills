Для разворачивания проекта необходимо выполнить следующее:
1)Установить gradle 1.8
2)В папке с проектом выполнить gradle clean build idea
3)Запустить IDEA и открыть проект через jobskills.ipr в папке проекта
4)Дальше наверху добавить конфигурацию для запуска проекта через:
Edit Configurations -> + -> Groovy -> Script Path = $PROJECT_PATH/build.gradle
Script Parameters = jettyRun
Остальные параметры пока вроде не важны, но с обрастанием проекта понадобятся =)


Для БД:
1)Установить  Postgres, создать бд job_skills
2)создать бд job_skills с владельцем postgres
3)зайти в ./conf/project.properties b прописать настройки к бд
3)выполнить в командной строке gradle update
4)запускаем проект
5)Вы великолепны!

