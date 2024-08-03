#include<iostream>
#include<algorithm>
#include<vector>
#define MAX 1000001
using namespace std;

int main(){

    std::ios::sync_with_stdio(false);
	std::cin.tie(NULL);
    cout << "Start!\n";

    int K,N;
    cin >> K >> N;
    cout << K << " " << N << "!\n";

    int line[MAX];

    long long ave=0;
    long long mVal=0;


    for(int i=0;i<K;i++){
        cin >> line[i];
        ave+=line[i];
        cout << line[i] << "\n";
    }

    ave=ave/N;

    long long start=1;
    long long end=ave;

    while (start<=end)
    {
        cout << start << " - " << end << " , mid = ";
        long long count=0;

        long long mid=(start+end)/2;
        cout << mid <<" => ";

        for(int i=0;i<K;i++){
            count+=line[i]/mid;
        }

        if(count >= N){
            start=mid+1;
            mVal=max(mVal,mid);
        }
        else{
            end=mid-1;
        }
        
        cout << count << "\n";
    }
    

    cout << mVal << "\n";
}

