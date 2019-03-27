package furhatos.app.actor_theater.nlu

import furhatos.nlu.Intent
import furhatos.util.Language


/**
 * Intent 1 Romeo and Juliet (Romeo)
 */

class Section1Intent() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        // We mark "pizza" here as required, since it is not enough with any of the other words to trigger this intent
        return listOf("She speaks: O, speak again, bright angel! for thou art. As glorious to this night, being her my head As is a winged messenger of heaven. Unto the white-upturned wondering eyes Of mortals that fall back to gaze on him When he bestrides the lazy-pacing clouds And sails upon the bosom of the air.",
                "next ")
    }

}

/**
 * Intent 1 Romeo and Juliet (Juliet)
 */

class Section1bIntent() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        // We mark "pizza" here as required, since it is not enough with any of the other words to trigger this intent
        return listOf("O Romeo, Romeo! wherefore are you Romeo? Deny the father and refuse the name;", "next ")
    }

}

/**
 * Intent 1 Romeo and Juliet (Romeo)
 */

class Section2bIntent() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        // We mark "pizza" here as required, since it is not enough with any of the other words to trigger this intent
        return listOf("My ears have not yet drunk a hundred words Of that tongue’s utterance, yet I know the sound: Art thou not Romeo and a Montague?", "next ")
    }

}

/**
 * Intent 1 Romeo and Juliet (Juliet)
 */

class Section2Intent() : Intent() {
    override fun getExamples(lang: Language): List<String> {
        // We mark "pizza" here as required, since it is not enough with any of the other words to trigger this intent
        return listOf("Shall I hear more, or shall I speak at this?",  "next ")
    }

}
