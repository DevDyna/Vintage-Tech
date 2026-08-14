---
navigation:
  title: Mechanical Farmland
  icon: "vintagetech:mechanical_farmland"
  parent: motion.md
categories:
  - consumer
item_ids:
  - vintagetech:mechanical_farmland
position: 9
---

# Mechanical Farmland

A powered agricultural block that use mechanical motion and a liquid fertilizer to boost plants planted above it and auto-harvest when fully grow

<GameScene zoom="3" interactive={true}>
  <Block x="0" y="0" z="0" id="vintagetech:mechanical_farmland" p:axis="x" p:enabled="true"  p:inverted="false"/>
  <Block x="0" y="1" z="0" id="vintagetech:hemp" p:age="2"/>
  <Block x="1" y="0" z="0" id="vintagetech:axle" p:axis="x" p:enabled="true" p:has_rope="false" p:inverted="false"/>
</GameScene>

<RecipeFor id="vintagetech:mechanical_farmland" />