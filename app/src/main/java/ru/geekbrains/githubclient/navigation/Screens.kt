package ru.geekbrains.githubclient.navigation

import androidx.fragment.app.Fragment
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.ui.fragments.UserFragment
import ru.geekbrains.githubclient.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


class Screens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? = UsersFragment.getInstance(0)
    }

    class UserScreen(private val user: GithubUser) : SupportAppScreen() {
        override fun getFragment(): Fragment? = UserFragment.getInstance(user)
    }
}
