---
navigation:
  title: Windmill
  icon: "vintagetech:windmill"
  parent: motion.md
categories:
  - generator
item_ids:
  - vintagetech:windmill
  - vintagetech:cloth
position: 4
---

# Windmill

A windmill produce motion to an entire network of axles and other transportation of motion blocks

Require a 1x5x5 empty area based on the facing to work

It can break during rain if not enclosed

It can be repaired using a <ItemLink id="vintagetech:cloth"/>

<GameScene zoom="2" interactive={true}>
  <Block x="0" y="0" z="0" id="vintagetech:windmill"/>
  <Block x="0" y="0" z="1" id="vintagetech:axle" p:axis="z" p:enabled="true" p:has_rope="false" p:inverted="false" />
  <Block x="0" y="0" z="2" id="vintagetech:axle" p:axis="z" p:enabled="true" p:has_rope="false" p:inverted="false" />
</GameScene>

<RecipeFor id="vintagetech:windmill" />