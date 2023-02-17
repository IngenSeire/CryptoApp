# CryptoApp
На главном экране приложения предоставлен обновляемый в реальном времени список из 10 наиболее популярных криптовалют по нажатию на которые будет открыт новый экран с
более детальной информацией о выбранной криптовалюте. Также имеется горизонтальная ориентация, в таком случае экран будет "поделен" пополам, в одной части список, в 
другой - детальная информация по нажатию.
О использованных решениях и технологиях: приложение выстроено согласно принципов чистой архитектуры и инъекции зависимостей (на основе Dagger2). Отображение 
данных реализовано на RecyclerView(через ListAdapter и ItemCallback), экран с детальной информацией реализован фрагментом, тот же фрагмент используется при горизонтальной
ориентации. Загрузка данных происходит с использованием сервисов реализованных через WorkManager каждые 10 секунд, данные получаются с сервера согласно API, 
тут же используются корутины (suspend функция), что и позволяет сервису останавливать свое выполнение на 10 сек перед последующей итерацией. 
Также присутствует mapper в котором преобразуются классы из domain в dto из data слоя и обратно. Данные сохраняются в базу данных и берутся оттуда, 
таким образом даже без интернета будут ображены последняя актуальная информация.
On the main screen we can see a list of 10 most popular cryptocurrencys which updates every 10 seconds, on click second screen will be opened, there user can see more 
detailed information about selected coin. Also there is land orientation in which main screen "splited" in two, where in the first part will be located list and in the
second - detailed information if we click on some coin.
About technologies and realizations: the application is created according to the clean architecture and dependency injection principles (using Dagger2). List is created
using the RecyclerView(ListAdapter + ItemCallback), screen with detailed information realized with fragment, the same fragment is used as a part of main screen in 
the land orientation. Data loading occurs every 10 seconds using services (WorkManager), data loads from server according to the API, also here i used coroutines 
(suspend function), which allows service to stop itself for 10 seconds before the next iteratiom. Also there is a mapper, where i transfer dto classes to classes
from domain and vice versa. The data saves to the database, so we can see the most actual information even without connection.
