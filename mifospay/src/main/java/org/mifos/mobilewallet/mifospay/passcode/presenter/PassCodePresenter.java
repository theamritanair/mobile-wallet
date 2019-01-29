package org.mifos.mobilewallet.mifospay.passcode.presenter;

import org.mifos.mobilewallet.core.base.UseCaseHandler;
import org.mifos.mobilewallet.core.data.fineract.api.FineractApiManager;
import org.mifos.mobilewallet.core.domain.usecase.client.FetchClientData;
import org.mifos.mobilewallet.core.domain.usecase.user.AuthenticateUser;
import org.mifos.mobilewallet.mifospay.base.BaseView;
import org.mifos.mobilewallet.mifospay.data.local.PreferencesHelper;
import org.mifos.mobilewallet.mifospay.passcode.PassCodeContract;

import javax.inject.Inject;

/**
 * This class contains the components of the Presenter required for the PassCode
 * @author ankur
 * @since 15/May/2018
 */
public class PassCodePresenter implements PassCodeContract.PassCodePresenter {
    private final UseCaseHandler mUsecaseHandler;
    private final PreferencesHelper preferencesHelper;
    @Inject
    AuthenticateUser authenticateUserUseCase;
    @Inject
    FetchClientData fetchClientDataUseCase;
    private PassCodeContract.PassCodeView mPassCodeView;


    @Inject
    public PassCodePresenter(UseCaseHandler useCaseHandler, PreferencesHelper preferencesHelper) {
        this.mUsecaseHandler = useCaseHandler;
        this.preferencesHelper = preferencesHelper;
    }

    /**
     * This function attaches a view.
     * @param baseView The view which is set as the PassCodeView
     */
    @Override
    public void attachView(BaseView baseView) {
        mPassCodeView = (PassCodeContract.PassCodeView) baseView;
        mPassCodeView.setPresenter(this);
    }

    /**
     * This functions creates an authenticated service.
     */
    public void createAuthenticatedService() {
        FineractApiManager.createSelfService(preferencesHelper.getToken());
    }
}
