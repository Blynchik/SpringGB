import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gb.tasks.model.Performer;
import ru.gb.tasks.model.Session;
import ru.gb.tasks.repo.PerformerRepo;
import ru.gb.tasks.repo.SessionRepo;
import ru.gb.tasks.service.AuthService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private PerformerRepo performerRepo;

    @Mock
    private SessionRepo sessionRepo;

    @InjectMocks
    private AuthService authService;

    @Test
    void register_ShouldCallSaveMethodOnPerformerRepo_WhenPerformerDoesNotExist() {
        //given
        Performer performer = new Performer();
        performer.setName("testName");

        when(performerRepo.findByName(performer.getName())).thenReturn(Optional.empty());

        //when
        authService.register(performer);

        //then
        verify(performerRepo).save(performer);
    }

    @Test
    void login_ShouldCreateNewSession_WhenLoginIsSuccessful() {
        // given
        String username = "testUser";
        String password = "testPassword";

        Performer performer = new Performer();
        performer.setName(username);
        performer.setPassword(password);

        when(performerRepo.findByName(username)).thenReturn(Optional.of(performer));

        // when
        authService.login(username, password);

        // then
        ArgumentCaptor<Session> sessionCaptor = ArgumentCaptor.forClass(Session.class);
        verify(sessionRepo, times(1)).save(sessionCaptor.capture());
        Session savedSession = sessionCaptor.getValue();
        assertThat(savedSession.getPerformer()).isEqualTo(performer);
    }

    @Test
    void logout_ShouldEndSession_WhenUserHasActiveSession() {
        // Arrange
        Long userId = 1L;

        Performer performer = new Performer();
        Session session = new Session();
        performer.setSession(session);

        when(performerRepo.findById(userId)).thenReturn(Optional.of(performer));

        // Act
        boolean result = authService.logout(userId);

        // Assert
        verify(sessionRepo, times(1)).delete(session);
        assertTrue(result);
    }

    @Test
    void logout_ShouldNotEndSession_WhenUserHasNoActiveSession() {
        // Arrange
        Long userId = 1L;

        Performer performer = new Performer(); // Здесь не устанавливаем сессию
        when(performerRepo.findById(userId)).thenReturn(Optional.of(performer));

        // Act
        boolean result = authService.logout(userId);

        // Assert
        verify(sessionRepo, never()).delete(any());
        assertFalse(result);
    }
}
