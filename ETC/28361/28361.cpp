#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int N;

int main(){
    ios_base :: sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;
    cout << N << "\n";

    int num=1;
    cout << num << " ";

    if(N%3==2){
        num++;
        cout << num << " ";
    }
    while(true){
        num+=2;
        cout << num << " ";
        if(N%3==0 && num==N){
            cout << num-1 << " 1\n";
            break;
        }
        
        num--;
        cout << num << " ";
        if(N%3==2 && num==N){
            cout << 1 << "\n";
            break;
        }
        num+=2;
        cout << num << " ";

        if((N%3 == 1 || N%3==2) && num==N){
            cout << 1 << "\n";
            break;
        }
    }
}