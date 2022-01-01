# Ad-Mediator-SDK

[![](https://jitpack.io/v/MortezaNedaei/Mediator-SDK.svg)](https://jitpack.io/#MortezaNedaei/Mediator-SDK)


## Description

Ad-Mediator-SDK is an Android library based on modern Android application tech-stacks acts as a
mediator for various advertisement networks (TapsellAds and UnityAds)

<a href="url"><img src="https://github.com/MortezaNedaei/Mediator-SDK/blob/dev/art/1.png" width="350" height="700"></a>
<a href="url"><img src="https://github.com/MortezaNedaei/Mediator-SDK/blob/dev/art/2.png" width="350" height="700"></a>
<a href="url"><img src="https://github.com/MortezaNedaei/Mediator-SDK/blob/dev/art/3.png" width="1000" height="200"></a>
<a href="url"><img src="https://github.com/MortezaNedaei/Mediator-SDK/blob/dev/art/4.png" width="1000" height="200"></a>

## Sub Tasks

- [x] First Offline
- [x] Architectural Patterns
- [x] Design Patterns
- [x] UnitTest

## Install
implementation 'com.github.MortezaNedaei:Mediator-SDK:1.0.0'


## Tech stack & Open-source libraries

- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/)
  based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
  + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
  for asynchronous.
- Dagger Hilt for dependency injection (DI).
- Android JetPack
    - Room Persistence - construct a database using the abstract layer.
- Architecture
    - Clean Architecture like
    - Repository pattern
- Testing
    - junit
    - mockk
    - truth
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and network
  data.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.

## Architecture

Application is based on repository pattern and clean like architecture





