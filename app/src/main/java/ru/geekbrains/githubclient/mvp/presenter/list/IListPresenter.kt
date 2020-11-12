package ru.geekbrains.githubclient.mvp.presenter.list

import ru.geekbrains.githubclient.mvp.view.IItemView

interface IListPresenter<V : IItemView?> {
    fun onItemClick(view: V)
    fun bindView(view: V)
    val count: Int
}