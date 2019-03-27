package furhatos.app.actor_theater.flow


import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.*

/**
 * Initial state
 */

val Idle: State = state {

    init {

        furhat.setVoice(Language.ENGLISH_US, Gender.FEMALE)
        if (users.count > 0) {
            furhat.attend(users.random)
            goto(Start)
        }

        furhat.say(greeting)
        goto(Start)
    }

    onEntry {
        System.out.println("Im in onEntry of state Idle")
        furhat.attendNobody()
    }

    onUserEnter {
        System.out.println("Im in onUserEntry of state Idle")
        furhat.attend(it)
        goto(Start)
    }
}

/**
 * State which manages the interaction
 */
val Interaction: State = state {

    onUserLeave(instant = true) {
        if (users.count > 0) {
            if (it == users.current) {
                furhat.attend(users.other)
                goto(Start)
            } else {
                furhat.glance(it)
            }
        } else {
            goto(Idle)
        }
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
    }

}