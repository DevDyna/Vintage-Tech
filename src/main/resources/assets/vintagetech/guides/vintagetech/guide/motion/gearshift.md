---
navigation:
  title: Gearshift
  icon: "vintagetech:gearshift"
  parent: motion.md
categories:
  - transfer
item_ids:
  - vintagetech:gearshift
position: 3
---

# Gearshift

The gearshift lets you rotate a section of a rotational-power network allowing the player to invert direction based blocks

<GameScene zoom="3" interactive={true}>
  <Block x="0" y="0" z="0" id="vintagetech:gearshift" p:axis="x" p:enabled="true" p:inverted="false" p:powered="false"/>

  <Block x="1" y="0" z="0" id="vintagetech:axle" p:axis="x" p:enabled="true" p:has_rope="false" p:inverted="false"/>
  <Block x="-1" y="0" z="0" id="vintagetech:axle" p:axis="x" p:enabled="true" p:has_rope="false" p:inverted="false"/>

  <Block x="0" y="1" z="0" id="minecraft:lever" p:face="floor" p:facing="north" p:powered="false"/>

  <Block x="0" y="0" z="3" id="vintagetech:gearshift" p:axis="x" p:enabled="true" p:inverted="true" p:powered="true"/>

  <Block x="1" y="0" z="3" id="vintagetech:axle" p:axis="x" p:enabled="true" p:has_rope="false" p:inverted="false"/>
  <Block x="-1" y="0" z="3" id="vintagetech:axle" p:axis="x" p:enabled="true" p:has_rope="false" p:inverted="true"/>

  <Block x="0" y="1" z="3" id="minecraft:lever" p:face="floor" p:facing="north" p:powered="true"/>

<BoxAnnotation color="#FF0000" min="2.25 0.25 0.25" max="2 0.75 0.75">
       Generator
  </BoxAnnotation>

<BoxAnnotation color="#FF0000" min="2.25 0.25 3.25" max="2 0.75 3.75">
       Generator
  </BoxAnnotation>

</GameScene>

<RecipeFor id="vintagetech:gearshift" />