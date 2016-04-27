import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

SbtScalariform.scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(IndentSpaces, 2)
  .setPreference(IndentWithTabs, false)
  .setPreference(SpaceBeforeColon, false)
  .setPreference(SpaceInsideParentheses, false)
  .setPreference(SpaceInsideBrackets, false)
  .setPreference(PreserveDanglingCloseParenthesis, true)
