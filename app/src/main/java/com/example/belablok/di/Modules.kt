package com.example.belablok.di

import androidx.room.Room
import com.example.belablok.*
import com.example.belablok.common.DATABASE_NAME
import com.example.belablok.repositories.AuthenticationRepository
import com.example.belablok.repositories.GameRoundRepository
import com.example.belablok.repositories.PlayersRepository
import com.example.belablok.repositories.PostsRepository
import com.example.belablok.ui.viewmodels.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
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

    viewModel { NewPostViewModel(get()) }

    viewModel { ProfileViewModel(get()) }

}

val repositoryModule = module {
    single { GameRoundRepository(get()) }

    single { AuthenticationRepository(get()) }

    single { PlayersRepository() }

    single { PostsRepository(get(), get()) }
}

val firebaseModule = module {
    single { FirebaseAuth.getInstance() }

    single { FirebaseStorage.getInstance() }

    single { FirebaseFirestore.getInstance() }
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
