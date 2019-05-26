package b.com.gittest.api

import b.com.gittest.data.model.User
import b.com.gittest.data.source.user.RemoteUserDataSource
import b.com.gittest.data.source.user.UserRepository
import b.com.gittest.ui.main.api.ApiContract
import b.com.gittest.ui.main.api.ApiPresenter
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.*

class ApiUnitTest {

    @Mock
    lateinit var apiView: ApiContract.View

    private lateinit var presenter: ApiPresenter
    private lateinit var apiViewInOder: InOrder

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpExSchedulers() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
    }

    @Before
    fun setUp() {
        //기본 셋팅
        MockitoAnnotations.initMocks(this)
        presenter = ApiPresenter(apiView, UserRepository.getInstance(RemoteUserDataSource))
        apiViewInOder = inOrder(apiView)

        //기본 찾기 테스트
        presenter.search("bba0", false)
        //리스트 초기화 코드 호출
        apiViewInOder.verify(apiView, times(1)).removeAllData()
        Thread.sleep(2000)
        //리스트 적용 코드 호출
        apiViewInOder.verify(apiView, times(1)).addUserData(ArgumentMatchers.anyList())

    }

    @Test
    fun moreLoad() {
        presenter.moreData()
        Thread.sleep(2000)
        //리스트 적용 코드 호출
        apiViewInOder.verify(apiView, times(1)).addUserData(ArgumentMatchers.anyList())
    }

    @Test
    fun like() {
        presenter.setLike(presenter.getSearchResultFirstId())
        apiViewInOder.verify(apiView, times(1)).updateUserData(User(presenter.getSearchResultFirstId()))
    }
}