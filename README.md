# AirfareTracker - приложение для поиска авиабилетов

# Основные технологии
### Kotlin
### Coroutines
### Flow
### Dagger
### Glide
### MVVM
### AdapterDelegates
### Clean Architecture
### Многомодульность


# Задачи
### 1. Выполнить базовую настройку проекта ✅
#### 1.1. Добавить зависимости в libs.versions.toml ✅
#### 1.2. Внедрить Gradle convention plugin ✅
#### 1.3. Выполнить разделение на модули ✅
### 2. Добавить основные общие ресурсы ✅
#### 2.1. Цвета ✅
#### 2.2. Типографика ✅
#### 2.3. Стили ✅
### 3. Разработать bottom navigation bar ✅
#### 3.1. "Авиабилеты" ✅
#### 3.2. "Отели" ✅
На экране заглушка с надписью "Отели" по центру
#### 3.3. "Короче" ✅
На экране заглушка с надписью "Короче" по центру
#### 3.4. "Подписки" ✅
На экране заглушка с надписью "Подписки" по центру
#### 3.5. "Профиль" ✅
На экране заглушка с надписью "Профиль" по центру
#### 3.6. Реализовать навигацию между экранами ✅
### 4. Разработать главный экран поиска дешевых авиабилетов ✅
#### 4.1. Хардкор надпись "Поиск дешевых авиабилетов" ✅
#### 4.2. CustomViewGroup для ввода места отправления и места прибытия ✅
#### 4.2.1 Поле места отправления ✅
Чтение/запись последнего значения из/в SharedPreferences. Подсказка "Откуда - Москва", если данных в кеше нет. Для ввода доступна только кириллица
#### 4.2.2 Поле места прибытия ✅
Подсказка "Куда - Турция". Для ввода доступна только кириллица
#### 4.3. Хардкор налпись "Музкально отлететь" ✅
#### 4.4. RecyclerView лента предложений ✅
### 5. Разработать модальное окно ✅
#### 5.1. CustomViewGroup для ввода места прибытия ✅
#### 5.2. Лента подсказок ✅
#### 5.2.1 "Сложный маршрут" ✅
При нажатии открывается стабовый экран
#### 5.2.2 "Куда угодно" ✅
При нажатии в поле ввода места прибытия подставляется текст
#### 5.2.3 "Выходные" ✅
При нажатии открывается стабовый экран
#### 5.2.4 "Горячие билеты" ✅
При нажатии открывается стабовый экран
#### 5.3. Лента рекомендаций ✅
При нажатии на каждый элемент в поле ввода места прибытия подставляется город
### 6. Экран списка билетов ✅
#### 6.1. CustomViewGroup, которая отображает введенное место отправления и прибытия
#### 6.1.1. Кнопка для возврата назад 
#### 6.1.1. Кнопка, которая меняет местами место отправления и место прибытия ✅
#### 6.2. Горизонтальный скролл с кнопками
#### 6.2.1. Кнопка даты обратного билета
При клике отображается нативный календарь
#### 6.2.2. Кнопка даты отправления
Отображается текущая дата. При клике отображается нативный календарь для смены даты.
#### 6.2.3. Кнопка числа пассажиров и класса
Хардкор, нефункциональный элемент
#### 6.2.4. Кнопка фильтров
Нефункциональный элемент
#### 6.3. RecyclerView лента рекомендаций билетов
#### 6.3. Кнопка поиска билетов
При нажатии открывается экран для просмотра всех билетов
#### 6.4. Switch для подписки на цену
Нефункционалый элемент

