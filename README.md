# Weather app

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