package ru.geekbrains.githubclient.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.githubclient.GithubApplication
import ru.geekbrains.githubclient.mvp.view.IMainView
import ru.geekbrains.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router


class MainPresenter : MvpPresenter<IMainView>() {
    private val router: Router? = GithubApplication.application?.router
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router?.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router?.exit()
    }
}