P R A G M A   f o r e i g n _ k e y s = O F F ; 
 
 B E G I N   T R A N S A C T I O N ; 
 
 C R E A T E   T A B L E   I F   N O T   E X I S T S   " M a r c a s "   ( 
 
 	 I D   I N T E G E R   N O T   N U L L   P R I M A R Y   K E Y   A U T O I N C R E M E N T , 
 
 	 N O M B R E   T E X T ( 3 0 )   N O T   N U L L 
 
 ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 1 , ' A l f a   R o m e o ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 2 , ' A u d i ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 3 , ' B A I C ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 4 , ' B M W ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 5 , ' C H E R Y ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 6 , ' C h e v r o l e t ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 7 , ' C h r y s l e r ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 8 , ' C i t r % e n ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 9 , ' D o d g e ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 1 0 , ' D S ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 1 1 , ' F I A T ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 1 2 , ' F o r d ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 1 3 , ' G e e l y ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 1 4 , ' H o d a ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 1 5 , ' H y u n d a i ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 1 6 , ' J e e p ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 1 7 , ' K i a ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 1 8 , ' M e r c e d e s   B e n z ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 1 9 , ' N i s s a n ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 2 0 , ' P e u g e o t ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 2 1 , ' R e n a u l t ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 2 2 , ' T o y o t a ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 2 3 , ' V o l k s w a g e n ' ) ; 
 
 I N S E R T   I N T O   M a r c a s   V A L U E S ( 2 4 , ' V o l v o ' ) ; 
 
 C R E A T E   T A B L E   I F   N O T   E X I S T S   " M o d e l o s "   ( 
 
 	 I D   I N T E G E R   N O T   N U L L   P R I M A R Y   K E Y   A U T O I N C R E M E N T , 
 
 	 I D _ M A R C A   I N T E G E R   N O T   N U L L , 
 
 	 D E S C R I P C I O N   T E X T   N O T   N U L L , 
 
 	 C O N S T R A I N T   M o d e l o _ M a r c a _ F K   F O R E I G N   K E Y   ( I D _ M A R C A )   R E F E R E N C E S   " M a r c a s " ( I D ) 
 
 ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 1 , 1 , ' 1 4 7   5 P   2 , 0   T S   D I S T I N C T I V E ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 2 , 2 , ' A 4   4 P   1 , 8   T F S I   A T T R A C T I O N   M U L T I ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 3 , 3 , ' X 3 5   5 P   1 , 5   T   C V T   F A S H I O N ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 4 , 4 , ' 3 2 5   C O U P E   2 , 5   i   E X E C U T I V E ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 5 , 5 , ' F u l w i n   5 P   1 , 5 ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 6 , 6 , ' A g i l e   5 P   1 , 4   L T Z ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 7 , 7 , ' 3 0 0   4 P   5 , 7   C   H E M I   A T X ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 8 , 8 , ' C 3   A i r c r o s s   5 P   1 , 6   1 6 V   S E G U R I D A D ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 9 , 9 , ' J o u r n e y   5 P   2 , 4   S E   2 F   A T   ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 1 0 , 1 0 , ' D S   3   3 P   1 , 6   V T i   S P O R T   C H I C ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 1 1 , 1 1 , ' 5 0 0   2 P   1 , 4   1 6 V   1 0 0 C V   S P O R T ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 1 2 , 1 2 , ' E c o s p o r t   5 P   1 , 6   F R E E S T Y L E ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 1 3 , 1 3 , ' 5 1 5   5 P   1 , 5   H A T C H   G S ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 1 4 , 1 4 , ' A c c o r d   4 P   2 , 0   T   E X T   1 0 A T ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 1 5 , 1 5 , ' S a n t a   F e   5 P   2 , 7   V 6   4 W D   G L S   7 P ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 1 6 , 1 6 , ' C h e r o k e e   5 P   2 , 8   C R D   S P O R T   A T ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 1 7 , 1 7 , ' C e r a t o   4 P   1 , 6   E X   F O R T E ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 1 8 , 1 8 , ' A 2 0 0   5 P   B L U E E F F I C I E N C Y   S T Y L E   ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 1 9 , 1 9 , ' L e a f   5 P   T E K N A ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 2 0 , 2 0 , ' 2 0 7   3 P   1 , 6   X T   C O M P A C T   P R E M I U M ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 2 1 , 2 1 , ' C a p t u r   5 P   1 , 6   L E   C O P   S P O R T I F   C V T ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 2 2 , 2 2 , ' E t i o s   5 P   1 , 5   X   C / A U D I O ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 2 3 , 2 3 , ' G o l   T r e n d   3 P   1 , 6   G P   P A C K   I   ' ) ; 
 
 I N S E R T   I N T O   M o d e l o s   V A L U E S ( 2 4 , 2 4 , ' C 3 0   3 P   2 , 0   A T   P 2   ' ) ; 
 
 C R E A T E   T A B L E   E m p l e a d o s   ( 
 
 	 L E G A J O   I N T E G E R   N O T   N U L L   P R I M A R Y   K E Y   A U T O I N C R E M E N T , 
 
 	 N O M B R E   T E X T ( 3 0 )   N O T   N U L L , 
 
 	 A P E L L I D O   T E X T ( 5 0 )   N O T   N U L L , 
 
 	 T E L E F O N O _ C O N T A C T O   I N T E G E R   N O T   N U L L 
 
 ) ; 
 
 I N S E R T   I N T O   E m p l e a d o s   V A L U E S ( 1 , ' R a m i r o ' , ' D a v i d ' , 3 5 1 9 0 0 0 0 0 1 ) ; 
 
 I N S E R T   I N T O   E m p l e a d o s   V A L U E S ( 2 , ' M a r i o ' , ' P r u e b a s ' , 3 5 1 9 0 0 0 0 0 2 ) ; 
 
 I N S E R T   I N T O   E m p l e a d o s   V A L U E S ( 3 , ' G e r m % n ' , ' D a r % n ' , 3 5 1 9 0 0 0 0 0 3 ) ; 
 
 C R E A T E   T A B L E   V e h i c u l o s   ( 
 
 	 I D   I N T E G E R   N O T   N U L L   P R I M A R Y   K E Y   A U T O I N C R E M E N T , 
 
 	 P A T E N T E   T E X T   N O T   N U L L , 
 
 	 I D _ M O D E L O   I N T E G E R   N O T   N U L L ,   A N I O   I N T E G E R   D E F A U L T   ( 2 0 1 9 )   N O T   N U L L , 
 
 	 C O N S T R A I N T   V e h i c u l o s _ M o d e l o s _ F K   F O R E I G N   K E Y   ( I D _ M O D E L O )   R E F E R E N C E S   M o d e l o s ( I D ) 
 
 ) ; 
 
 I N S E R T   I N T O   V e h i c u l o s   V A L U E S ( 1 , ' Z Z 0 0 0 Z Z ' , 1 , 2 0 1 5 ) ; 
 
 I N S E R T   I N T O   V e h i c u l o s   V A L U E S ( 2 , ' Z Z 1 1 1 A A ' , 2 , 2 0 1 9 ) ; 
 
 I N S E R T   I N T O   V e h i c u l o s   V A L U E S ( 3 , ' Z Z 0 1 2 F T ' , 3 , 2 0 2 4 ) ; 
 
 I N S E R T   I N T O   V e h i c u l o s   V A L U E S ( 4 , ' Z Z 0 0 1 G G ' , 4 , 2 0 1 6 ) ; 
 
 I N S E R T   I N T O   V e h i c u l o s   V A L U E S ( 5 , ' Z Z Z 0 0 1 ' , 5 , 2 0 0 4 ) ; 
 
 I N S E R T   I N T O   V e h i c u l o s   V A L U E S ( 6 , ' Z Z 4 4 4 A A ' , 8 , 2 0 1 7 ) ; 
 
 C R E A T E   T A B L E   P o s i c i o n e s   ( 
 
 	 I D   I N T E G E R   N O T   N U L L   P R I M A R Y   K E Y   A U T O I N C R E M E N T , 
 
 	 I D _ V E H I C U L O   I N T E G E R   N O T   N U L L , 
 
 	 F E C H A _ H O R A   T I M E S T A M P   D E F A U L T   ( C U R R E N T _ T I M E S T A M P )   N O T   N U L L , 
 
 	 L A T I T U D   R E A L   N O T   N U L L , 
 
 	 L O N G I T U D   R E A L   N O T   N U L L , 
 
 	 C O N S T R A I N T   P o s i c i o n e s _ V e h i c u l o s _ F K   F O R E I G N   K E Y   ( I D _ V E H I C U L O )   R E F E R E N C E S   V e h i c u l o s ( I D ) 
 
 ) ; 
 
 C R E A T E   T A B L E   P r u e b a s   ( 
 
 	 I D   I N T E G E R   N O T   N U L L   P R I M A R Y   K E Y   A U T O I N C R E M E N T , 
 
 	 I D _ V E H I C U L O   I N T E G E R   N O T   N U L L , 
 
 	 I D _ I N T E R E S A D O   I N T E G E R   N O T   N U L L , 
 
 	 I D _ E M P L E A D O   I N T E G E R   N O T   N U L L , 
 
 	 F E C H A _ H O R A _ I N I C I O   T I M E S T A M P   D E F A U L T   ( C U R R E N T _ T I M E S T A M P )   N O T   N U L L , 
 
 	 F E C H A _ H O R A _ F I N   T I M E S T A M P   D E F A U L T   ( C U R R E N T _ T I M E S T A M P )   N O T   N U L L , 
 
 	 C O M E N T A R I O S   T E X T ( 5 0 0 ) , 
 
 	 C O N S T R A I N T   P r u e b a s _ I n t e r e s a d o s _ F K   F O R E I G N   K E Y   ( I D _ I N T E R E S A D O )   R E F E R E N C E S   I n t e r e s a d o s ( I D ) , 
 
 	 C O N S T R A I N T   P r u e b a s _ V e h i c u l o s _ F K   F O R E I G N   K E Y   ( I D _ V E H I C U L O )   R E F E R E N C E S   V e h i c u l o s ( I D ) , 
 
 	 C O N S T R A I N T   P r u e b a s _ E m p l e a d o s _ F K   F O R E I G N   K E Y   ( I D _ E M P L E A D O )   R E F E R E N C E S   E m p l e a d o s ( L E G A J O ) 
 
 ) ; 
 
 C R E A T E   T A B L E   I F   N O T   E X I S T S   " N o t i f i c a c i o n e s " 
 
 ( 
 
         I D                 I N T E G E R 
 
                 p r i m a r y   k e y   a u t o i n c r e m e n t , 
 
         M E N S A J E       T E X T   n o t   n u l l , 
 
         T I P O             T E X T   n o t   n u l l , 
 
         T E L E F O N O     T E X T   n o t   n u l l , 
 
         F E C H A           D A T E T I M E   d e f a u l t   C U R R E N T _ T I M E S T A M P , 
 
         I D _ P R U E B A   I N T E G E R 
 
                 c o n s t r a i n t   N o t i f i c a c i o n e s _ P r u e b a s _ F K 
 
                         r e f e r e n c e s   P r u e b a s 
 
 ,   T I P O _ I N C I D E N T E   T E X T ) ; 
 
 C R E A T E   T A B L E   I F   N O T   E X I S T S   " Z o n a s _ P e l i g r o s a s " 
 
 ( 
 
         I D                       I N T E G E R 
 
                 p r i m a r y   k e y   a u t o i n c r e m e n t , 
 
         N O M B R E               T E X T   n o t   n u l l , 
 
         L A T _ N O R O E S T E   R E A L   n o t   n u l l , 
 
         L O N _ N O R O E S T E   R E A L   n o t   n u l l , 
 
         L A T _ S U R E S T E     R E A L   n o t   n u l l , 
 
         L O N _ S U R E S T E     R E A L   n o t   n u l l 
 
 ) ; 
 
 C R E A T E   T A B L E   I F   N O T   E X I S T S   " I n t e r e s a d o s " 
 
 ( 
 
         I D                                                   I N T E G E R                                   n o t   n u l l 
 
                 p r i m a r y   k e y   a u t o i n c r e m e n t , 
 
         T I P O _ D O C U M E N T O                           T E X T ( 3 )   d e f a u l t   ' " D N I " '   n o t   n u l l , 
 
         D O C U M E N T O                                     T E X T ( 1 0 )                                 n o t   n u l l , 
 
         N O M B R E                                           T E X T ( 5 0 )                                 n o t   n u l l , 
 
         A P E L L I D O                                       T E X T ( 5 0 )                                 n o t   n u l l , 
 
         N R O _ L I C E N C I A                               I N T E G E R                                   n o t   n u l l , 
 
         F E C H A _ V E N C I M I E N T O _ L I C E N C I A   T I M E S T A M P                               n o t   n u l l , 
 
         R E S T R I N G I D O                                 I N T E G E R 
 
 ) ; 
 
 I N S E R T   I N T O   s q l i t e _ s e q u e n c e   V A L U E S ( ' V e h i c u l o s ' , 6 ) ; 
 
 I N S E R T   I N T O   s q l i t e _ s e q u e n c e   V A L U E S ( ' P o s i c i o n e s ' , 0 ) ; 
 
 I N S E R T   I N T O   s q l i t e _ s e q u e n c e   V A L U E S ( ' M a r c a s ' , 2 4 ) ; 
 
 I N S E R T   I N T O   s q l i t e _ s e q u e n c e   V A L U E S ( ' M o d e l o s ' , 2 4 ) ; 
 
 I N S E R T   I N T O   s q l i t e _ s e q u e n c e   V A L U E S ( ' E m p l e a d o s ' , 3 ) ; 
 
 I N S E R T   I N T O   s q l i t e _ s e q u e n c e   V A L U E S ( ' N o t i f i c a c i o n e s ' , 0 ) ; 
 
 I N S E R T   I N T O   s q l i t e _ s e q u e n c e   V A L U E S ( ' Z o n a s _ P e l i g r o s a s ' , 0 ) ; 
 
 I N S E R T   I N T O   s q l i t e _ s e q u e n c e   V A L U E S ( ' I n t e r e s a d o s ' , 0 ) ; 
 
 C R E A T E   U N I Q U E   I N D E X   V e h i c u l o _ P A T E N T E _ I D X   O N   V e h i c u l o s   ( P A T E N T E ) ; 
 
 C O M M I T ; 
 
 