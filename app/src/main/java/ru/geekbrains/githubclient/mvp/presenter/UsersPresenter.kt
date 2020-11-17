package ru.geekbrains.githubclient.mvp.presenter

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.githubclient.GithubApplication
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo
import ru.geekbrains.githubclient.mvp.presenter.list.IUserListPresenter
import ru.geekbrains.githubclient.mvp.view.IUserItemView
import ru.geekbrains.githubclient.mvp.view.IUsersView
import ru.geekbrains.githubclient.navigation.Screens
import ru.terrakok.cicerone.Router
import java.util.*

class UsersPresenter : MvpPresenter<IUsersView?>() {
    private val compositeDisposable = CompositeDisposable()
    private val usersRepo: GithubUserRepo = GithubUserRepo()
    private val router: Router? = GithubApplication.application?.router

    inner class UsersListPresenter : IUserListPresenter {
        val users: MutableList<GithubUser> = ArrayList()

        override fun onItemClick(view: IUserItemView) {
            router?.navigateTo(Screens.UserScreen(users[view.pos]))
        }

        override fun bindView(view: IUserItemView) {
            val user: GithubUser = users[view.pos]
            view.setLogin(user.login)
        }

        override val count: Int
            get() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState?.init()
        loadData()
    }

    private fun loadData() {
        val usersDisposable = usersRepo.users
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { users ->
                            usersListPresenter.users.addAll(users)
                            viewState?.updateList()
                        },
                        { error -> error.printStackTrace() }
                )
        compositeDisposable.add(usersDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
    fun backPressed(): Boolean {
        router?.exit()
        return true
    }
}

