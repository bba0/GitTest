package b.com.gittest.local

import b.com.gittest.data.source.user.RemoteUserDataSource
import b.com.gittest.data.source.user.UserRepository
import b.com.gittest.ui.main.local.LocalContract
import b.com.gittest.ui.main.local.LocalPresenter
import org.junit.Before
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class LocalUnitTest {
    @Mock
    lateinit var localView: LocalContract.View

    private lateinit var presenter: LocalPresenter
    private lateinit var mLocalViewInOrder: InOrder


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LocalPresenter(localView, UserRepository.getInstance(RemoteUserDataSource))
        mLocalViewInOrder = inOrder(localView)
    }
}