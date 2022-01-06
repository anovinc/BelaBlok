package com.example.belablok.di

import androidx.room.Room
import com.example.belablok.BelaBlokDatabase
import com.example.belablok.GameRoundDao
import com.example.belablok.common.DATABASE_NAME
import com.example.belablok.repositories.AuthenticationRepository
import com.example.belablok.repositories.GameRoundRepository
import com.example.belablok.repositories.PlayersRepository
import com.example.belablok.ui.viewmodels.*
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { (position: Int) -> GameMainViewModel(position, get(), get()) }

    viewModel { PlayersEntryViewModel(get()) }

    viewModel { GameEndViewModel(get(), get()) }

    viewModel { NewGameRoundViewModel(get()) }

    viewModel { UserRegistrationViewModel(get()) }

    viewModel { UserSignUpViewModel(get()) }

    viewModel { SplashActivityViewModel() }

    viewModel { UserSignOutViewModel(get()) }

    viewModel { GameSelectionViewModel(get()) }

}

val repositoryModule = module {
    single { GameRoundRepository(get()) }

    single { AuthenticationRepository(get()) }

    single { PlayersRepository() }
}

val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
}

val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            BelaBlokDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideGameRoundDao(belaBlokDatabase: BelaBlokDatabase): GameRoundDao {
        return belaBlokDatabase.gameRoundDao()
    }

    single {
        provideGameRoundDao(get())
    }
}

val modules = listOf(
    roomDatabaseModule,
    repositoryModule,
    firebaseModule,
    viewModelModule
)
