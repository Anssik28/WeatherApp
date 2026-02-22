# Weather app

## DEMO LINKKI - https://youtu.be/5lifBsVli1s

### Mitä Retrofit tekee

- Hoitaa HTTP-pyyntöjen tekemisen OpenWeather API:lle
- Muuttaa API-kutsun Kotlin-funktioksi (interface)

### Miten JSON muutetaan dataluokiksi

- Gson muuntaa API:n palauttaman JSON-datan automaattisesti Kotlin-dataluokiksi
- Kenttien nimet vastaavat API:n JSON-rakennetta

### Miten coroutines toimii tässä

- API-kutsu tehdään taustasäikeessä 'viewModelScope.launc' avulla
- UI ei jäädy verkko-operaation aikana
- Kun data saapuu, UI päivittyy automaattisesti

### Miten UI-tila toimii

- ViewModel hallitsee UI-tilaa (uiState, isLoading, errorMessage)
- Compose reagoi tilamuutoksiin automaattisesti (recomposition)

### Miten API-avain on tallennettu

- API-avain tallennetaan 'local.properties' -tiedostoon
- Gradle siirtää sen 'BuildConfig' -kenttään
- Retrofit käyttää 'BuildConfig.OPENWEATHER_API_KEY' arvoa API-kutsussa

### Mitä Room tekee
- **Entity** (WeatherEntity) määrittelee Room-tietokantaan tallennettavan sään rakenteen
- **DAO** (WeatherDao) sisältää tietokantakyselyt
- **Database** (AppDatabase) on Room-tietokannan pääluokka
- **Repository** (WeatherRepository) huolehtii datan lähteistä (API+Room)
- **ViewModel** hallitsee sovelluksen tilaa ja logiikkaa
- **UI** (Compose) reagoi ViewModelin tilamuutoksiin automaattisesti

### Projektin rakenne

```
app/
└─ src/
└─ main/
└─ java/com/example/weatherapp/
├─ data/
│  ├─ local/
│  │  ├─ WeatherDao.kt
│  │  └─ AppDatabase.kt
│  ├─ model/
│  │  ├─ WeatherEntity.kt
│  │  └─ WeatherResponse.kt
│  ├─ remote/
│  │  ├─ RetrofitInstance.kt
│  │  └─ WeatherApi.kt
│  └─ repository/
│     └─ WeatherRepository.kt
├─ ui/
│  └─ WeatherScreen.kt
└─ viewmodel/
   └─ WeatherViewModel.kt 
```
### Miten datavirta kulkee
1. Käyttäjä syöttää kaupungin ja painaa "Hae sää"
2. ViewModel kutsuu repositorya
3. Repository hakee sään OpenWeather API:sta
4. Tulos tallennetaan Room-tietokantaan
5. ViewModel lukee datan Roomista
6. UI näyttää Roomista luetun datan

### Miten välimuistilogiikka toimii
- Viimeisin haettu sää tallennetaan Roomiin
- Room toimii paikallisena välimuistina
- ViewModel lukee datan Roomista UI:lle
