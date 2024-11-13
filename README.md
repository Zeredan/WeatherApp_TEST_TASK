# Тестовое задание "Прогноз погоды"
## Как поставить себе
> Здесь приложена информация о том, как получить у себя этот проект и начать с ним работать
1) Скачайте android studio, если у вас ее нет
2) В любой свободной папке(при наличии свободного места) откройте git-консоль и пропишите `git clone https://github.com/Zeredan/WeatherApp_TEST_TASK`
3) Затем откройте эту папку через android studio и нажмите "yes, i trust", когда спросят разрешение о безопасности
4) Попробуйте забилдить проект(Ctrl+F9) и запустить его(app модуль)
5) Если будут ошибки(например такое может быть с версиями android studio, то попробуйте поработать с версией android studio и gradle. Данный проект был выполнен на *Android Studio 2023 hedgehod*
## Реализованный функционал
1) Релизована возможность просмотра текущей погоды и погоды на 48 часов вперед, подробно для каждого часа
2) Реализована возможность просмотра погоды на 8 дней вперед, подробно для каждого дня
3) Реализована возможность узнать погоду не только в текущем местоположении, но и в указанном городе/локации
4) Поскольку у меня нет подписки на openweathermapapi, то я реализовал ввод(и запоминание в дальнейшем) api-ключа, каждым пользователем(смотреть Меню Настроек)
5) Релизована возможность сохранения выбранных настроек(с помощью Room)
6) Релизован доступ в интернет для получения информации о погоде, и получение геоданных как с телефона, так и с названия мест
## Использованные Технологии
1) Clean architecture - Весь проект разделен на 3 слоя: `UI`, `Domain`, `Data`. UI - занимается пользовательским интерфейсом и включает в себя App, Feature, Core модули.
   Domain - реализует бизнес-логику, включает в себя Feature, Core модули. Data - реализуется Data модулями и предоставляет удобные для использования репозитории и классы для работы с внешними данными
2) Многомодульность - Весь проект так же разделен на 4 основных типа модулей: `App`(1), `Feature`(3), `Data`(2), `Core`(1). App модуль - точка входа в приложение
   Feature модуль - отдельный по смыслу экранчик или несколько экранчиков(их столько же, сколько и компонент навигации в MainActivity). В нем создается UI и ViewModel для конкретной фичи. Feature модули
   не знают ни про друг друга, ни про App модули, так что общение происходит через передаваемые в них функции, а так же репозитории с данными. Data модули - предоставляют данные, классы, репозитории,
   и Dagger-DI для легкого инжекта себя. Их используют Feature модули. Core модули - предоставляют *ОБЩИЕ* функции и действия, как в UI, так и в Domain слоях
3) MVVM - Model View ViewModel - отличный структурный паттерн, который разделяет UI и бизнес-логику. Это - часть Feature модуля, View отвечает за UI, ViewModel отвечает за хранение стейтов и предоставляет coroutineScope,
 который живет дольше, чем activity или часть compose snapshot tree, а так включает в себя бизнес-логику, и работу с Model - это, как правило - use cases, которые работают с репозиториями.
4) JetpackCompose - реактивный UI, работает лучше, чем обычный, не нужен лишний Presenter класс и ui-логика для обновления по состояниям. Все происходит засчет рекомпозициях в snapshot tree
5) Retrofit - REST Фреймворк для работы с сетью
6) Room - локальное андроид-хранилище, орм для базы данных
7) Dagger 2 - DI, позволяет легко создать dependency граф и резолвить данные.
## Скриншоты приложения
![изображение](https://github.com/user-attachments/assets/00879e90-4d4e-4fab-9662-9c2a621b10e8)

Фича 1 - экран для текущей погоды и просмотра по часам

![изображение](https://github.com/user-attachments/assets/565e04ea-1507-431f-a492-6e2c3b5dfc35)

Фича 1 - экран для просмотра подробной информации для выбранного часа

![изображение](https://github.com/user-attachments/assets/867991ad-86cd-407a-89e7-36efa1dfbc86)

Фича 1 - экран во время загрузки данных

![изображение](https://github.com/user-attachments/assets/3fb5ccee-76bf-4f69-b0da-cd30bcaf0cfb)

Фича 2 - экран для показа погоды на 8 дней вперед

![изображение](https://github.com/user-attachments/assets/d3755aed-2aa0-49e1-99a9-4714de99f578)

Фича 2 - экран для просмотра подробной информации для выбранного дня

![изображение](https://github.com/user-attachments/assets/dd9999c2-ebd9-4717-b16f-affa0edf8765)

Фича 3 - экран настроек