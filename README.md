# Nearby

Este é um aplicativo Android desenvolvido em Kotlin para facilitar parcerias entre estabelecimentos e usuários do aplicativo. Os parceiros disponibilizam **cupons de desconto** que podem ser resgatados pelos usuários ao lerem um **QRCode presencialmente** nos estabelecimentos. 

---

## ✨ Funcionalidades

1. **Mapa Interativo**:
   - Tela inicial com **Google Maps**, exibindo os estabelecimentos parceiros mapeados por **categorias** (ex.: Restaurante, Cinema, Hotel, etc.).
   - Os estabelecimentos são destacados no mapa com marcadores específicos por categoria.

2. **Lista de Estabelecimentos Próximos**:
   - Um menu rolável na parte inferior da tela com **fotos e detalhes** de estabelecimentos parceiros próximos, filtrados pela categoria com cupons disponíveis.

3. **Resgate de Cupons**:
   - Leitura de **QRCode** no estabelecimento para resgatar cupons de desconto.

4. **Backend Personalizado**:
   - Inicialmente, o app utiliza um **backend fornecido durante o evento**.
   - Um **novo backend será desenvolvido em Java**, e este README será atualizado com as informações necessárias após sua conclusão.

---

## 🛠️ Configuração do Projeto

### Pré-requisitos

1. **Chave da API do Google Maps**:
   - Adicione sua chave no arquivo `AndroidManifest.xml` no seguinte trecho de código:
   - site para regatar sua api : [google_maps_api](https://developers.google.com/maps/documentation/javascript/get-api-key?hl=pt-br)

     ```xml
     <meta-data
         android:name="com.google.android.geo.API_KEY"
         android:value="YOUR_API_KEY_HERE" />
     ```

2. **Configuração da Porta do Backend**:
   - No arquivo `core/network/NearbyRemoteDatasource`, configure a porta correta para executar o aplicativo no dispositivo:

     ```kotlin
     private const val LOCAL_HOST_EMULATOR_BASE_URL = "http://10.0.2.2:3333"
     private const val LOCAL_HOST_PHISICAL_BASE_URL = "http://192.168.x.x"

     private const val BASE_URL = LOCAL_HOST_EMULATOR_BASE_URL // ou LOCAL_HOST_PHISICAL_BASE_URL
     ```

   - Use:
     - `10.0.2.2` para o **emulador Android**.
     - O IP local da sua máquina para **dispositivos físicos**.

---

## 🔧 Tecnologias Utilizadas

- **Kotlin**
- **Android SDK**
- **Google Maps API**
- **Ktor** para requisições HTTP
- **Backend** (atualmente em desenvolvimento em **Java**)

---

## 🚀 Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/parceria-cupom-app.git
