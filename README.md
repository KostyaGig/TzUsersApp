# TzUsersApp

## Description
Получение юзеров с интернета,отображение их в списке,сохранение их в кеш для быстрой и автономной работы

## Main Stack : Retrofit,Coroutines,LiveData,Mvvm,Dagger,View binding <br/>
Image loader: Picasso <br/>
SOLID,OOP,Clen architecture,JUnit tests<br/><br/>
Разбиение приложения на data, domain, presentation слои<br/>
Маппинг обЪектов через слои<br/><br/>
В качестве предоставления зависимостей(di) я использовал "Dagger2"<br/><br />
Для навигации приложения была использована библиотека "Navigation component"

### Tests

Необходимые классы покрыты юнит - тестами

<img 
src="https://firebasestorage.googleapis.com/v0/b/mvvmfirebase-b3347.appspot.com/o/test_results.png?alt=media&token=22fe7639-7d60-4a61-a4ad-6822eab99bbe"/>

#### package core
 - [UserMapperTest](https://github.com/KostyaGig/TzUsersApp/blob/master/app/src/test/java/com/zinoview/tzusersapp/core/UserMapperTest.kt)<br/>
 - [UsersMapperTest](https://github.com/KostyaGig/TzUsersApp/blob/master/app/src/test/java/com/zinoview/tzusersapp/core/UsersMapperTest.kt)<br/>

#### package data
- [UsersRepositoryTest](https://github.com/KostyaGig/TzUsersApp/blob/master/app/src/test/java/com/zinoview/tzusersapp/data/UsersRepositoryTest.kt)<br/>
- [ExceptionMapperTest](https://github.com/KostyaGig/TzUsersApp/blob/master/app/src/test/java/com/zinoview/tzusersapp/data/ExceptionMapperTest.kt)<br/>

### Разбиение приложение на фичи 

### Feature UA01 show users list (данную фичу можно протестировать согласно тесткейсам)

[UA01TestCases](https://github.com/KostyaGig/TzUsersApp/blob/master/app/src/main/java/com/zinoview/tzusersapp/testcases/UA01TestCases)<br/>

#### Progress state 

<img src="https://firebasestorage.googleapis.com/v0/b/mvvmfirebase-b3347.appspot.com/o/photo_2021-11-16_20-37-26.jpg?alt=media&token=64b87474-de42-47b4-8af8-dbee1a2fb0ca" width="300" height="550"/>

Состояние ошибки,в данном случае я отключил интернет
#### Error state 

<img src="https://firebasestorage.googleapis.com/v0/b/mvvmfirebase-b3347.appspot.com/o/photo_2021-11-16_20-37-28.jpg?alt=media&token=9dd2ca31-71c8-4b4a-8ae5-c320d072de7d" width="300" height="550"/>

#### Success state (в тулбаре отображается место,с которого поучены данные)
При самом первом запуске устройства мы получаем данные с интернета

<img src="https://firebasestorage.googleapis.com/v0/b/mvvmfirebase-b3347.appspot.com/o/photo_2021-11-16_20-37-30.jpg?alt=media&token=7e5cefb9-5a87-487c-84a1-dba6b5c3f1d6" width="300" height="550"/>

### Feature UA02 show users list cache (данную фичу можно протестировать согласно тесткейсам)

[UA02TestCases](https://github.com/KostyaGig/TzUsersApp/blob/master/app/src/main/java/com/zinoview/tzusersapp/testcases/UA02TestCases)<br/>

Получение данных с кеша. При первом получение данных с интернета мы сохраняем их в кеш.<br />

Если в кеше есть данные,то берем их от туда,в обратном случае мы будем делать запрос в сеть,чтобы получить данные

#### Считывание данных с кеша(в тулбаре отображается место,с которого поучены данные)

Стоит отметить,что если данные считываются с кеша,то появляется возможность их редактировать,а также удалять (с кеша)

<img src="https://firebasestorage.googleapis.com/v0/b/mvvmfirebase-b3347.appspot.com/o/photo_2021-11-16_20-48-32.jpg?alt=media&token=4b5e33db-d46d-40ba-866d-b780e5f6d961" width="300" height="550"/>

### Feature UA03 modify cached users (данную фичу можно протестировать согласно тесткейсам)

[UA03TestCases](https://github.com/KostyaGig/TzUsersApp/blob/master/app/src/main/java/com/zinoview/tzusersapp/testcases/UA03TestCases)<br/>

Удаление,редактирование юзеров,хранящихся в кеше

#### Редактирование данных юзера 

<img src="https://firebasestorage.googleapis.com/v0/b/mvvmfirebase-b3347.appspot.com/o/photo_2021-11-16_20-48-40.jpg?alt=media&token=09a3072a-7a64-46d3-b355-77910fb27f65" width="300" height="550"/>

#### Результат редактирования имени и фамилии юзера

<img src="https://firebasestorage.googleapis.com/v0/b/mvvmfirebase-b3347.appspot.com/o/photo_2021-11-16_20-48-32.jpg?alt=media&token=4b5e33db-d46d-40ba-866d-b780e5f6d961" width="300" height="550"/>

#### Удаление юзеров из кеша

<img src="https://firebasestorage.googleapis.com/v0/b/mvvmfirebase-b3347.appspot.com/o/photo_2021-11-16_20-48-38.jpg?alt=media&token=c9680ea6-f3e1-41c3-b9b0-b5c47c2d15ba" width="300" height="550"/>

### Feature UA04 details about user (данную фичу можно протестировать согласно тесткейсам)

[UA04TestCases](https://github.com/KostyaGig/TzUsersApp/blob/master/app/src/main/java/com/zinoview/tzusersapp/testcases/UA04TestCases)<br/>

#### По нажатии на юзера отображается подробная информация о нем

<img src="https://firebasestorage.googleapis.com/v0/b/mvvmfirebase-b3347.appspot.com/o/photo_2021-11-16_20-57-29.jpg?alt=media&token=9e3c6a15-5337-40f4-86a3-b6d43d2c930f" width="300" height="550"/>
