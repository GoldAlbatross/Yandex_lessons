package com.example.sprint16_architecture.core.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sprint16_architecture.R
import com.example.sprint16_architecture.core.navigation.NavigatorHolder
import com.example.sprint16_architecture.core.navigation.NavigatorImpl
import com.example.sprint16_architecture.core.ui.movies.MoviesFragment
import org.koin.android.ext.android.inject

class RootActivity: AppCompatActivity(R.layout.activity_root) {

    // Заинжектили NavigatorHolder, чтобы прикрепить к нему Navigator
    private val navigatorHolder: NavigatorHolder by inject()
    // Создали Navigator
    private val navigator = NavigatorImpl(
        fragmentContainerViewId = R.id.rootFragmentContainerView,
        fragmentManager = supportFragmentManager
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            navigator.openFragment(MoviesFragment())
        }
    }

    // Прикрепляем Navigator к NavigatorHolder
    override fun onResume() {
        super.onResume()
        navigatorHolder.attachNavigator(navigator)
    }

    // Открепляем Navigator от NavigatorHolder
    override fun onPause() {
        super.onPause()
        navigatorHolder.detachNavigator()
    }
}