package ru.geekbrains.githubclient.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.githubclient.R
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser
import ru.geekbrains.githubclient.mvp.presenter.UserPresenter
import ru.geekbrains.githubclient.mvp.view.IUserView
import ru.geekbrains.githubclient.ui.BackButtonListener

class UserFragment : MvpAppCompatFragment(), IUserView, BackButtonListener {

    private val userPresenter: UserPresenter by moxyPresenter { UserPresenter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = arguments?.getParcelable<GithubUser>(KEY)
        tv_login.text = user?.login
    }

    companion object {
        private const val KEY = "key"
        fun getInstance(user: GithubUser): UserFragment {
            val fragment = UserFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY, user)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun backPressed(): Boolean {
        return userPresenter.backPressed()
    }
}