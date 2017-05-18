## Computer System Architecture Project – Phase IV
###**Machine Simulation Usage Manual**
```
					By Priyanka More, Phani Gadepalli, Akshay Thombre, Siddarth Gandhi
```
####Console UI:


The console UI can be used to:
1. Deposit data into a specific memory location using the Memory panel.
2. Execute a single instruction using the Instruction panel.
3. Deposit data into or display data from the General Purpose Registers, Index Registers or the Memory.
4. Executing Program 1(finding closest number in a series of 20 numbers), 2 (pattern search in 6sentences) and 3(float and vector operations demo)
5. Can be used to turn on or off the simulator. - RUN/HALT button

### Instruction set:

Phase I:

- (001) LDR r, x, address [,I] 
- (002) STR r, x, address [,I] 
- (003) LDA r, x, address [,I] 
- (041) LDX x, address [,I] 
- (042) STX x, address [,I]
- (004) AMR r, x, address [,I] 
- (005) SMR r, x, address [,I] 
- (006) AIR r, immed 
- (007) SIR r, immed

Phase II:

- (010) JZ r, x, address [,I]
- (011) JNE r, x, address [,I]
- (012) JCC cc, x, address [,I]
- (013) JMA x, address [,I]
- (014) JSR x, address [,I]
- (015) RFS immed
- (016) SOB r, x, address [,I]
- (017) JGE r, x, address [,I]
- (020) MLT rx, ry
- (021) DVD rx, ry
- (022) TRR rx, ry
- (023) AND rx, ry
- (024) ORR rx, ry
- (025) NOT rx
- (031) SRC r, count, L/R, A/L
- (032) RRC r, count, L/R, A/L
- (061) IN r, devid
- (062) OUT r, devid
- (063) CHK r, devid
- (000) HLT
- (036->065) TRAP code

Phase IV:

- FADD, FSUB, CNVRT
- VADD, VSUB
- LDFR, STFR
