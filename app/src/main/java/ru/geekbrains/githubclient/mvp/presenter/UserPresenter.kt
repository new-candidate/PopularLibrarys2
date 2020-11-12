package ru.geekbrains.githubclient.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.githubclient.GithubApplication
import ru.geekbrains.githubclient.mvp.view.IUsersView
import ru.geekbrains.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router

class UserPresenter : MvpPresenter<IUsersView?>() {
    private val router: Router? = GithubApplication.application?.router

    fun backPressed(): Boolean {
        router?.backTo(Screens.UsersScreen())
        return false
    }
}