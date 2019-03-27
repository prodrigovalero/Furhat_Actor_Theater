package furhatos.app.actor_theater.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*

import furhatos.app.actor_theater.nlu.*;
import furhatos.gestures.Gestures
import furhatos.gestures.Gestures.BigSmile
import furhatos.gestures.Gestures.ExpressAnger
import furhatos.gestures.Gestures.Smile
import furhatos.util.Gender
import furhatos.util.Language

var count = 0
/**
 * State in which the concrete functionalities of this skill start.
 */
val Start: State = state(Interaction) {


    onEntry {
        System.out.println("Hey there")
        furhat.ask("Are you ready to start?")
    }


    onResponse<Yes> {
        furhat.say("Let's get to it")
        goto(Branches)


    }

    onResponse<No> {
        furhat.say("I'll wait, but don't take to long")
        furhat.gesture(Smile)
        delay(4000)
        goto(loop)
    }


}
/**
 * State in which the role to be played by Furhat is determined
 */
val Branches: State = state(Interaction)
{
    onEntry {
        delay(500)
        furhat.ask("Should I do Juliet and you do Romeo?")
    }

    onResponse<Yes> {

        goto(BranchJuliet)

    }
    onResponse<No> {
        goto(BranchRomeo)
    }


}

/**
 * State of preparation for the role of Romeo of the performance Romeo and Juliet
 */
val BranchRomeo: State = state(Interaction) {
    onEntry {
        furhat.setVoice(Language.ENGLISH_US, Gender.MALE)
        goto(Section1)
    }
}

/**
 * State of the preparation for role of Juliet of the performance Romeo and Juliet
 */
val BranchJuliet: State = state(Interaction) {
    onEntry {
        furhat.setVoice(Language.ENGLISH_US, Gender.FEMALE)
        goto(Section1b)
    }
}

/**
 * 1,2,3...ACTION --> The performance has started (Furhat -> Romeo)
 */

val Section1: State = state(Interaction) {
    onEntry {
        furhat.ask(HamletSection1)
        delay(5000)

    }
    System.out.println("Say this now!: O Romeo, Romeo! wherefore are you Romeo? Deny the father and refuse the name;")

    onResponse<Section1bIntent> {
        delay(1000)
        goto(Section2)
    }

    onResponse {
        addOnetoCount()
        goto(RestartFunction)
    }

}

/**
 * 1,2,3...ACTION --> The performance has started (Furhat -> Juliet)
 */

val Section1b: State = state(Interaction) {
    onEntry {
        furhat.ask("You can start: ")

    }
    System.out.println("Say this now!: She speaks: O, speak again, bright angel! for thou art. As glorious to this night, being her my head As is a winged messenger of heaven. Unto the white-upturned wondering eyes Of mortals that fall back to gaze on him When he bestrides the lazy-pacing clouds And sails upon the bosom of the air.\"")
    onResponse<Section1Intent> {
        delay(4000)
        goto(Section2b)

    }

    onResponse {
        addOnetoCount()
        goto(RestartFunction)
    }

    onNoResponse {
        furhat.say(" You were supposed to talk now, let's start again")
        goto(RestartFunction)
    }

}

/**
 * Performance continues (Furhat -> Romeo)
 */

val Section2: State = state(Interaction) {
    onEntry {
        furhat.ask(HamletSection2)

    }
    System.out.println("My ears have not yet drunk a hundred words Of that tongue’s utterance, yet I know the sound: Art thou not Romeo and a Montague? ")

    onResponse<Section2Intent> {
        delay(6000)
        furhat.say(" I told you, with effort nothing is impossible, well done this was all for today", async = true)
        furhat.gesture(BigSmile(strength = 3.0, duration = 7.0))
    }

    onResponse {
        addOnetoCount()
        goto(RestartFunction)
    }

}

/**
 * Performance continues (Furhat -> Juliet)
 */

val Section2b: State = state(Interaction) {
    onEntry {
        furhat.ask(HamletSection1b)


    }
    System.out.println("Say it now! Shall I hear more, or shall I speak at this? ")
    onResponse<Section2bIntent> {
        delay(6000)
        furhat.say(HamletSection2b, async = false)
        furhat.say(" I told you, with effort nothing is impossible, well done this was all for today", async = true)
        furhat.gesture(BigSmile(strength = 3.0, duration = 7.0))
    }

    onResponse {
        addOnetoCount()
        goto(RestartFunction)
    }
}

/**
 * Function which restarts the performance if the number of mistakes is less than 3, else it stops!
 */

val RestartFunction: State = state(Interaction) {
    onEntry {


        delay(2000)

        if (numErrors() >= 3) {
            furhat.say("You have already done 3 errors, this is unbelivable, study and come back tommorow!", async = true)
            furhat.gesture(ExpressAnger(duration = 2.0))
        } else {
            furhat.say(Restarting)
            delay(1000)
            goto(loop)
        }
    }
}

/**
 *
 * State which restarts the performance
 *
 */

val loop: State = state(Interaction) {
    onEntry {


        furhat.say("I hope you are ready now")
        delay(1000)
        goto(Start)
    }
}

/**
 * Function which adds one to the error counter
 */

fun addOnetoCount() {
    count += 1
}

/**
 * Function which returns the total number of errors during the performance of the user
 */
fun numErrors(): Int {
    System.out.println("Retourning count: " + count)
    return count
}

//TEXTS


/**
 * Text + Emotions of the presentation of Furhat and its skill
 */
val greeting = utterance {
    +"Hi there,"
    +Gestures.Smile
    +"Welcome to the Furhat Theater Academy, today we are going to rehearse a well-known play, Hamlet"
    +delay(500)
    +"I am sure you have rehearse and you know your text well, because I know mine"
    +Gestures.BigSmile()
    +delay(2000)

}
/**
 * Text Romeo and Juliet (Romeo)
 */
val HamletSection1 = utterance {
    +delay(500)
    +"She speaks: O, speak again, bright angel! for thou art. As glorious to this night, being her my head As is a winged messenger of heaven. Unto the white-upturned wondering eyes Of mortals that fall back to gaze on him When he bestrides the lazy-pacing clouds And sails upon the bosom of the air."
}
/**
 * Text Romeo and Juliet (Juliet)
 */
val HamletSection1b = utterance {
    +delay(500)
    +"O Romeo, Romeo! wherefore are you Romeo? Deny the father and refuse the name;"
}
/**
 * Text Romeo and Juliet (Romeo)
 */
val HamletSection2 = utterance {
    +delay(500)
    +"Shall I hear more, or shall I speak at this?"
}
/**
 * Text Romeo and Juliet (Juliet)
 */
val HamletSection2b = utterance {
    +delay(500)
    +"My ears have not yet drunk a hundred words Of that tongue’s utterance, yet I know the sound: Art thou not Romeo and a Montague?"
}
/**
 * Text which Furhat says when an error is made by the user during the performance and it is ready to start again
 */
val Restarting = utterance {
    +delay(500)
    +"You did a small mistake, don't worry let's start again but be careful"
}
